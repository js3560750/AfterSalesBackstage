package com.jinsong.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinsong.mapper.RepairMapper;
import com.jinsong.model.Repair;
import com.jinsong.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	RepairMapper repairMapper;

	@Override
	public long insertRepair(Repair repair) {
		Date date = new Date();
		repair.setApplyTime(date);
		repair.setGmtCreate(date);
		repair.setGmtModified(date);
		return repairMapper.insert(repair)>0?repair.getId():0;
	}

}
