package com.jinsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinsong.model.Engineer;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;

@Service
public interface CompanyService {

	/**
	 * 管理员提交新的repair工单
	 */
	long insertRepair(Repair repair);
	
	/**
	 * 管理员提交新的maintain工单
	 */
	long insertMaintain(Maintain maintain);
	
	/**
	 * 管理员提交新的install工单
	 */
	long insertInstall(Install install);
	
	/**
	 * 获得所有工程师
	 */
	List<Engineer> listEngineer();
	
	 /**
     * 获得status=null也就是“未处理”的repair工单
     */
    List<Repair> selectByStautsNull();
    
    /**
     * 获得所有repair工单
     */
    List<Repair> selectAll();
    
    /**
     * 根据id获得单个工单
     */
    Repair selectById(long id);
    
    /**
     * 更新repair工单的工程师信息
     */
    long updateRepairEngineer(long id,String engineer);
    
    /**
     * 获得所有install工单
     */
	List<Install> selectAllInstall();
	
	/**
	 * 获得所有maintain工单
	 */
	List<Maintain> selectAllMaintain();
	
}
