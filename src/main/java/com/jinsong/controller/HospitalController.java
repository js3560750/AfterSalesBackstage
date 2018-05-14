package com.jinsong.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jinsong.mapper.EngineerMapper;
import com.jinsong.model.Engineer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.jinsong.model.Repair;
import com.jinsong.service.HospitalService;
import com.jinsong.util.JsUtil;

@RestController
@RequestMapping("/weixin")
public class HospitalController {

    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @Autowired
    HospitalService hospitalService;

    @Autowired
    EngineerMapper engineerMapper;


    /**
     * 医生提交repair工单
     *
     * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
     */
    @PostMapping("/repair")
    public String insertRepair(@ModelAttribute Repair repair) {

        try {
            //服务返回>0成功，否则失败
            //但是返回给前端的code=0表示成功
            if (hospitalService.insertRepair(repair) > 0) {
                //短信给公司管理员提示
                Engineer engineer = engineerMapper.selectByName("报修接收号码");
                String tel = engineer.getTel();
                String type = "新的医院报修";
                String hospitalName = repair.getHospitalName();
                String orderNumber = repair.getOrderNumber();
                smsNewRepair(tel, type, orderNumber, hospitalName);

                //如果是质检报修工单，则短信给公司质检负责人
                if (hospitalName.equals("质检")) {
                    Engineer qualityEngineer = engineerMapper.selectByName("质检接收号码");
                    String qualityTel = qualityEngineer.getTel();
                    String qualityType = "新的质检报修";
                    smsNewRepair(qualityTel, qualityType, orderNumber, hospitalName);
                }

                String hospitalTel = repair.getHospitalTel();
                smsNewRepairSuccess(hospitalTel);

                return JsUtil.getJSONString(0, "提交成功");
            }
        } catch (Exception e) {
            logger.error("提交repair工单失败" + e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 医生提交repair工单上传的图片
     */
    @PostMapping("/repairimg")
    public String insertRepairImg(@RequestParam("uploadImg") MultipartFile file, HttpServletRequest request) {
        try {
            if (hospitalService.insertRepairImg(file, request) > 0) {
                return JsUtil.getJSONString(0, "提交成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }


    /**
     * 根据医生的微信昵称获取状态不等于“已完成”的订单
     */
    @PostMapping("/repair/{openid}/list/unfinish")
    @ResponseBody
    public List<Repair> selectUnfinishByOpenid(@PathVariable("openid") String openid) {
        return hospitalService.selectUnfinishByOpenid(openid);
    }

    /**
     * 根据医生的微信昵称获取其提交的所有工单
     */
    @PostMapping("/repair/{openid}/list")
    @ResponseBody
    public List<Repair> selectAllByOpenid(@PathVariable("openid") String openid) {

        return hospitalService.selectAllByOpenid(openid);

    }

    /**
     * 医生提交repair工单服务评价
     * <p>
     * <p>
     * !!!!!!!!!!!!!!!!!!!!!!!微信小程序的请求和POSTMAN的请求处理方式不一样！！！！！！！！！！！！
     * 这里是获得POST请求的单个数据，按理说直接用@RequestParam来接手就可以了，采用POSTMAN就可以的
     * 如下：
     *
     * @PostMapping("/repair/{id}/rate") public String updateRepairRate(@PathVariable("id") long id ,@RequestParam("serviceRating") Byte serviceRating)
     * <p>
     * <p>
     * 但用微信小程序的wx.request()就不行，必须改成下面这种@RequestBody的方式来接收参数
     */
    @PostMapping("/repair/{id}/rate")
    public String updateRepairRate(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {

        Byte serviceRating = Byte.parseByte(params.get("serviceRating").toString());

        try {
            if (hospitalService.updateRepairRate(id, serviceRating) > 0) {
                return JsUtil.getJSONString(0, "更新服务评价成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 医生提交repair工单的投诉
     */
    @PostMapping("/repair/{id}/complain")
    public String updateRepairComplaint(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
        String hospitalComplaint = params.get("hospitalComplaint").toString();

        try {
            if (hospitalService.updateRepairComplaint(id, hospitalComplaint) > 0) {
                return JsUtil.getJSONString(0, "更新医院投诉成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    private static void smsNewRepair(String phoneNumbers, String type, String orderNumber, String hospitalName) {

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI2xBqWSnMU8Cv";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "bsXXygtXrIFDYS7oS4W3iSol4fDXs2";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("金洹售后");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_133180165");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"type\":\"" + type + "\", \"orderNumber\":\"" + orderNumber + "\",\"hospitalName\":\"" + hospitalName + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        } else {
            System.out.println(sendSmsResponse.getMessage());
        }
    }

    private static void smsNewRepairSuccess(String hospitalTel) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI2xBqWSnMU8Cv";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "bsXXygtXrIFDYS7oS4W3iSol4fDXs2";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(hospitalTel);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("金洹售后");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_134260331");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{\"type\":\""+type+"\", \"orderNumber\":\""+orderNumber+"\",\"hospitalName\":\""+hospitalName+"\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        } else {
            System.out.println(sendSmsResponse.getMessage());
        }
    }
}
