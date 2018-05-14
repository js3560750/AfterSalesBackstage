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

import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

@RestController
@RequestMapping("/weixin")
public class EngineerWeiXinController {

    private static final Logger logger = LoggerFactory.getLogger(EngineerWeiXinController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    RepairMapper repairMapper;

    @PostMapping("/engineer/login")
    public String engineerLogin(@RequestBody Map<String, Object> params) {
        String account = params.get("account").toString();
        String password = params.get("password").toString();
        long flag = companyService.engineerLogin(account, password);

        try {
            if (flag == 1) {
                return JsUtil.getJSONString(0, "提交成功");
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

    @GetMapping("/{account}/unfinished")
    @ResponseBody
    public HashMap<String, Object> engineerUnfinished(@PathVariable("account") String account) {

        List<Repair> repairList = companyService.selectUnfinishedRepairByEngineer(account);
        List<Install> installList = companyService.selectUnfinishedInstallByEngineer(account);
        List<Maintain> maintainList = companyService.selectUnfinishedMaintainByEngineer(account);

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

    @GetMapping("/{account}/finished")
    @ResponseBody
    public HashMap<String, Object> engineerFinished(@PathVariable("account") String account) {

        List<Repair> repairList = companyService.selectFinishedRepairByEngineer(account);
        List<Install> installList = companyService.selectFinishedInstallByEngineer(account);
        List<Maintain> maintainList = companyService.selectFinishedMaintainByEngineer(account);

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

    @GetMapping("/repair/{id}/signIn")
    public String repairSignIn(@PathVariable("id") long id) {
        try {
            if (companyService.repairSignIn(id) > 0) {
                return JsUtil.getJSONString(0, "工程师签到成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @GetMapping("/install/{id}/signIn")
    public String installSignIn(@PathVariable("id") long id) {
        try {
            if (companyService.installSignIn(id) > 0) {
                return JsUtil.getJSONString(0, "工程师签到成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @GetMapping("/maintain/{id}/signIn")
    public String maintainSignIn(@PathVariable("id") long id) {
        try {
            if (companyService.maintainSignIn(id) > 0) {
                return JsUtil.getJSONString(0, "工程师签到成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @GetMapping("/repair/{id}/finish")
    public String repairFinish(@PathVariable("id") long id) {
        try {
            if (companyService.repairFinish(id) > 0) {
                Repair repair=repairMapper.selectByPrimaryKey(id);
                String hospitalTel = repair.getHospitalTel();
                String orderNumber =repair.getOrderNumber();
                smsFinishRepairSuccess(hospitalTel,orderNumber);
                return JsUtil.getJSONString(0, "工程师完成工单成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @GetMapping("/install/{id}/finish")
    public String installFinish(@PathVariable("id") long id) {
        try {
            if (companyService.installFinish(id) > 0) {
                return JsUtil.getJSONString(0, "工程师完成工单成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @GetMapping("/maintain/{id}/finish")
    public String maintainFinish(@PathVariable("id") long id) {
        try {
            if (companyService.maintainFinish(id) > 0) {
                return JsUtil.getJSONString(0, "工程师完成工单成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @PostMapping("/repair/update")
    public String repairUpdate(@RequestBody Map<String, Object> params) {

        try {
            if (companyService.updateRepair(params) > 0 && companyService.insertErrorByEngineer(params) > 0) {
                return JsUtil.getJSONString(0, "添加维修工单信息成功");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @PostMapping("/install/update")
    public String installUpdate(@ModelAttribute Install install) {

        try {
            if (companyService.updateInstall(install) > 0) {
                return JsUtil.getJSONString(0, "添加维修工单信息成功");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }

    @PostMapping("/maintain/update")
    public String maintainUpdate(@ModelAttribute Maintain maintain) {

        try {
            if (companyService.updateMaintain(maintain) > 0) {
                return JsUtil.getJSONString(0, "添加维修工单信息成功");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return JsUtil.getJSONString(1, "提交失败");
    }


    private static void smsFinishRepairSuccess(String hospitalTel,String orderNumber) {
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
        request.setTemplateCode("SMS_134260341");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"orderNumber\":\""+orderNumber+"\"}");
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
