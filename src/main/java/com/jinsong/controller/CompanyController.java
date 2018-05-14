package com.jinsong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jinsong.mapper.EngineerMapper;
import com.jinsong.mapper.InstallMapper;
import com.jinsong.mapper.MaintainMapper;
import com.jinsong.mapper.RepairMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinsong.model.Engineer;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

@RestController
@RequestMapping("/weixin")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    EngineerMapper engineerMapper;

    @Autowired
    RepairMapper repairMapper;

    @Autowired
    MaintainMapper maintainMapper;

    @Autowired
    InstallMapper installMapper;


    /**
     * 微信公司端账号登录
     * 只有authority=ROLE_ADMIN权限的账号返回成功
     */
    @PostMapping("/company/login")
    public String companyLogin(@RequestBody Map<String, Object> params) {
        String account = params.get("account").toString();
        String password = params.get("password").toString();
        long flag = companyService.companyLogin(account, password);

        try {
            if (flag == 1) {
                return JsUtil.getJSONString(0, "提交成功");
            } else if (flag == -2) {
                return JsUtil.getJSONString(-2, "权限不是ROLE_ADMIN");
            } else if (flag == -1) {
                return JsUtil.getJSONString(-1, "密码不对");
            } else if (flag == -3) {
                return JsUtil.getJSONString(-3, "用户名错误");
            }

        } catch (Exception e) {
            logger.error("提交登录表失败" + e.getMessage());
        }
        return JsUtil.getJSONString(1, "登录失败");

    }

    /**
     * 管理员提交新的repair工单
     *
     * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
     */
    @PostMapping("/companyrepair")
    public String insertRepair(@ModelAttribute Repair repair) {

        try {
            // 服务返回>0成功，否则失败
            // 但是返回给前端的code=0表示成功
            if (companyService.insertRepair(repair) > 0) {
                //如果提交成功，则短信通知
                String tel = repair.getEngineerTel();
                String type = "维修工单";
                String orderNumber = repair.getOrderNumber();
                String hospitalName = repair.getHospitalName();
                smsNewOrderSend(tel, type, orderNumber, hospitalName);

                String hospitalTel =repair.getHospitalTel();
                String engineer = repair.getEngineer();
                String engineerTel =repair.getEngineerTel();
                smsUpdateEngineerSuccess(hospitalTel,engineer,engineerTel);

                return JsUtil.getJSONString(0, "提交成功");
            }
        } catch (Exception e) {
            logger.error("提交repair工单失败" + e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 管理员提交新的maintain工单
     *
     * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
     */
    @PostMapping("/companymaintain")
    public String insertMaintain(@ModelAttribute Maintain maintain) {

        try {
            // 服务返回>0成功，否则失败
            // 但是返回给前端的code=0表示成功
            if (companyService.insertMaintain(maintain) > 0) {
                //如果提交成功，则短信通知
                String tel = maintain.getEngineerTel();
                String type = "保养工单";
                String orderNumber = maintain.getOrderNumber();
                String hospitalName = maintain.getHospitalName();
                smsNewOrderSend(tel, type, orderNumber, hospitalName);
                return JsUtil.getJSONString(0, "提交成功");
            }
        } catch (Exception e) {
            logger.error("提交repair工单失败" + e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 管理员提交新的install工单
     *
     * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
     */
    @PostMapping("/companyinstall")
    public String insertMaintain(@ModelAttribute Install install) {

        try {
            // 服务返回>0成功，否则失败
            // 但是返回给前端的code=0表示成功
            if (companyService.insertInstall(install) > 0) {
                //如果提交成功，则短信通知
                String tel = install.getEngineerTel();
                String type = "安装工单";
                String orderNumber = install.getOrderNumber();
                String hospitalName = install.getHospitalName();
                smsNewOrderSend(tel, type, orderNumber, hospitalName);


                return JsUtil.getJSONString(0, "提交成功");
            }
        } catch (Exception e) {
            logger.error("提交repair工单失败" + e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 获得所有工程师
     */
    @GetMapping("/engineer/list")
    public List<Engineer> listEngineer() {
        return companyService.listEngineer();
    }

    /**
     * 获得status=null也就是“未处理”的repair工单
     */
    @GetMapping("/repair/list/statusnull")
    public List<Repair> selectByStautsNull() {
        return companyService.selectByStautsNull();
    }

    ;

    /**
     * 获得所有repair工单
     */
    @GetMapping("/repair/list")
    public List<Repair> selectAllRepair() {
        return companyService.selectAll();
    }

    /**
     * 根据id获得单个repair工单
     */
    @GetMapping("repair/{id}")
    public Repair selectRepairById(@PathVariable("id") long id) {
        return companyService.selectById(id);
    }

    /*
     * 更新repair工单的工程师信息
     */
    @PostMapping("repair/{id}/engineer")
    public String updateRepairEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
        String engineer = params.get("engineer").toString();
        try {
            if (companyService.updateRepairEngineer(id, engineer) > 0) {
                //如果提交成功，则短信通知
                Engineer engineerModel =engineerMapper.selectByName(engineer);
                String tel =engineerModel.getTel();
                String type="维修工单";
                Repair repair =repairMapper.selectByPrimaryKey(id);
                String hospitalName=repair.getHospitalName();
                String orderNumber=repair.getOrderNumber();
                smsNewOrderSend(tel,type,orderNumber,hospitalName);
                return JsUtil.getJSONString(0, "订单委派工程师成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 获得所有install工单
     */
    @GetMapping("install/list")
    public List<Install> selectAllInstall() {
        return companyService.selectAllInstall();
    }

    /**
     * 根据id获得单个install工单
     */
    @GetMapping("install/{id}")
    public Install selectInstallById(@PathVariable("id") long id) {
        return companyService.selectInstallById(id);
    }

    /*
     * 更新install工单的工程师信息
     */
    @PostMapping("install/{id}/engineer")
    public String updateInstallEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
        String engineer = params.get("engineer").toString();
        try {
            if (companyService.updateInstallEngineer(id, engineer) > 0) {
                //如果提交成功，则短信通知
                Engineer engineerModel =engineerMapper.selectByName(engineer);
                String tel =engineerModel.getTel();
                String type="安装工单";
                Install install =installMapper.selectByPrimaryKey(id);
                String hospitalName=install.getHospitalName();
                String orderNumber=install.getOrderNumber();
                smsNewOrderSend(tel,type,orderNumber,hospitalName);
                return JsUtil.getJSONString(0, "订单委派工程师成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 获得所有maintain工单
     */
    @GetMapping("maintain/list")
    public List<Maintain> selectAllMaintain() {
        return companyService.selectAllMaintain();
    }

    /**
     * 根据id获得单个maintain工单
     */
    @GetMapping("maintain/{id}")
    public Maintain selectMaintainById(@PathVariable("id") long id) {
        return companyService.selectMaintainById(id);
    }

    /*
     * 更新maintain工单的工程师信息
     */
    @PostMapping("maintain/{id}/engineer")
    public String updateMaintainEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
        String engineer = params.get("engineer").toString();
        try {
            if (companyService.updateMaintainEngineer(id, engineer) > 0) {
                //如果提交成功，则短信通知
                Engineer engineerModel =engineerMapper.selectByName(engineer);
                String tel =engineerModel.getTel();
                String type="保养工单";
                Maintain maintain =maintainMapper.selectByPrimaryKey(id);
                String hospitalName=maintain.getHospitalName();
                String orderNumber=maintain.getOrderNumber();
                smsNewOrderSend(tel,type,orderNumber,hospitalName);
                return JsUtil.getJSONString(0, "订单委派工程师成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    /**
     * 搜索动作
     */
    @PostMapping("/order/search")
    @ResponseBody
    public HashMap<String, Object> orderSearchAction(@RequestBody Map<String, Object> params) {
        String searchInfo = params.get("searchInfo").toString();

        List<Repair> repairList = companyService.selectRepairBySearch(searchInfo);
        List<Install> installList = companyService.selectInstallBySearch(searchInfo);
        List<Maintain> maintainList = companyService.selectMaintainBySearch(searchInfo);

        HashMap<String, Object> map = new HashMap<>();

        if (repairList.size() != 0) {
            map.put("repairList", repairList);
        }
        if (installList.size() != 0) {
            map.put("installList", installList);
        }
        if (maintainList.size() != 0) {
            map.put("maintainList", maintainList);
        }

        return map;
    }

    private static void smsNewOrderSend(String phoneNumbers, String type, String orderNumber, String hospitalName) {
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
        request.setTemplateParam("{\"type\":\""+type+"\", \"orderNumber\":\""+orderNumber+"\",\"hospitalName\":\""+hospitalName+"\"}");
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
        }else{
            System.out.println(sendSmsResponse.getMessage());
        }
    }

    private static void smsUpdateEngineerSuccess(String hospitalTel,String engineer,String tel){
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
        request.setTemplateCode("SMS_134260336");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"engineer\":\""+engineer+"\", \"tel\":\""+tel+"\"}");
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
        }else{
            System.out.println(sendSmsResponse.getMessage());
        }
    }

}
