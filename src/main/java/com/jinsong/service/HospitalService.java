package com.jinsong.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinsong.model.Repair;

@Service
public interface HospitalService {

	/**
	 * 医生提交repair工单
	 */
	long insertRepair(Repair repair);
	
	
	/**
	 * 医生提交repair工单上传的图片
	 */
	long insertRepairImg(MultipartFile file, HttpServletRequest request);
	
    /**
     * 根据医生的微信昵称获取其提交的所有工单
     * 
     */
	List<Repair> selectAllByOpenid(String openid);
	
	
	/**
	 * 医生提交repair工单服务评价
	 */
	long updateRepairRate(long id,Byte serviceRating);
	
	/**
	 * 医生提交repair工单的投诉
	 */
	long updateRepairComplaint(long id,String hospitalComplaint);
}
