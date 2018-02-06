package com.jinsong.service;

import org.springframework.stereotype.Service;

import com.jinsong.model.Repair;

@Service
public interface HospitalService {

	/**
	 * 医生提交repair工单
	 */
	long insertRepair(Repair repair);
}
