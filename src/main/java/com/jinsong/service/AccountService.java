package com.jinsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinsong.model.Admin;
import com.jinsong.model.Engineer;
import com.jinsong.model.Product;

@Service
public interface AccountService {

	/**
	 * 添加engineer
	 */
	long insertEngineer(Engineer engineer);
	
	/**
	 * 根据id修改engineer
	 */
	long updateEngineer(Engineer engineer);
	
	/**
	 * 获得所有engineer
	 */
	List<Engineer> selectAllEngineer();
	
	/**
	 * 根据id删除engineer
	 */
	long deleteEngineerById(long id);
	
	
	/**
     * 通过姓名搜索engineer
     */
	List<Engineer> selectEngineerBySearch(String searchInfo);
	
	
	/**
	 * 添加管理员
	 */
	long insertAdmin(Admin admin);
	
	/**
	 * 根据id修改管理员
	 */
	long updateAdmin(Admin admin);
	
	/**
	 * 获得所有管理员
	 */
	List<Admin> selectAllAdmin();
	
	/**
	 * 根据id删除管理员
	 */
	long deleteAdminById(long id);
	
	/**
	 * 根据id获得单个管理员
	 */
	Admin selectAdminById(long id);
	
	
}
