package com.jinsong.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jinsong.controller.HospitalController;
import com.jinsong.mapper.ImageMapper;
import com.jinsong.mapper.RepairMapper;
import com.jinsong.model.Image;
import com.jinsong.model.Repair;
import com.jinsong.service.HospitalService;
import com.jinsong.util.JsUtil;

@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);

    @Autowired
    RepairMapper repairMapper;

    @Autowired
    ImageMapper imageMapper;

    @Override
    public long insertRepair(Repair repair) {
        // 日期
        Date date = new Date();
        repair.setApplyTime(date);
        repair.setGmtCreate(date);
        repair.setGmtModified(date);

        // 获取当前时间后2天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +2);// 当前时间+2天
        date = calendar.getTime();
        repair.setEstimatedArrivalTime(date);

        // 订单号
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        Random random = new Random();
        int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
        String orderNumber = "wx" + str + num;
        repair.setOrderNumber(orderNumber);

        // 订单状态
        repair.setStatus("未处理");

        return repairMapper.insert(repair) > 0 ? repair.getId() : 0;
    }

    @Override
    public long insertRepairImg(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {

        // 让当前线程等待5秒，以确保repair订单已经存入数据库，然后我们再从数据库中获得最新的订单号
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Repair repair = repairMapper.selectLastOne();
        String orderNumber = repair.getOrderNumber();

        if (file.isEmpty()) {
            logger.error("图片为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);

        //上传路径在项目根目录
        // 文件上传后的路径，注意最后还要加一个/，会存在img文件夹下
        // 这里的目录路径dest=/usr/tomcat/webapps/ROOT/WEB-INF/classes/static/img/fileName
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String filePath = "/img/";
        File dest = new File(path.getAbsolutePath(),"static/img/" + fileName);


        //上传路径在服务器绝对路径
        //上传的路径dest=/usr/share/nginx/html/resources/static/img/fileName
        //String filePath="/usr/share/nginx/html/resources/static/img/";
        //File dest = new File(filePath+fileName);
        //String filePath = "/img/";

        String imgUrl = filePath+fileName;

        // 若目录不存在创建目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }


        // 上传动作
        try {
            file.transferTo(dest);
            logger.info("上传成功");

            Image image = new Image();
            image.setOrderNumber(orderNumber);
            image.setImgUrl(imgUrl);
            return imageMapper.insert(image) > 0 ? image.getId() : 0;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Repair> selectAllByOpenid(String openid) {

        return repairMapper.selectAllByOpenid(openid);
    }

    @Override
    public long updateRepairRate(long id, Byte serviceRating) {
        Repair repair = repairMapper.selectByPrimaryKey(id);
        repair.setServiceRating(serviceRating);

        return repairMapper.updateByPrimaryKey(repair) > 0 ? repair.getId() : 0;
    }

    @Override
    public long updateRepairComplaint(long id, String hospitalComplaint) {
        Repair repair = repairMapper.selectByPrimaryKey(id);
        repair.setHospitalComplaint(hospitalComplaint);

        return repairMapper.updateByPrimaryKey(repair) > 0 ? repair.getId() : 0;
    }

    @Override
    public List<Repair> selectUnfinishByOpenid(String openid) {

        return repairMapper.selectUnfinishByOpenid(openid);
    }

}
