package com.jinsong.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jinsong.mapper.AdminMapper;
import com.jinsong.mapper.EngineerMapper;
import com.jinsong.model.Admin;
import com.jinsong.model.Engineer;
import com.jinsong.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	EngineerMapper engineerMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AdminMapper adminMapper;
	

	@Override
	public long insertEngineer(Engineer engineer) {
		
		//日期，注意Date用java.sql.Date，以防止前台验证报错 Validation failed for object
		Date date = new Date(new java.util.Date().getTime());
		engineer.setGmtCreate(date);
		engineer.setGmtModified(date);
	
		return engineerMapper.insert(engineer)>0?engineer.getId():0;
	}

	@Override
	public long updateEngineer(Engineer engineer) {
		
		return engineerMapper.updateByPrimaryKey(engineer)>0?engineer.getId():0;
	}

	@Override
	public List<Engineer> selectAllEngineer() {
		
		return engineerMapper.selectAll();
	}

	@Override
	public long deleteEngineerById(long id) {
		
		return engineerMapper.deleteByPrimaryKey(id)>0?1:0;
	}

	@Override
	public List<Engineer> selectEngineerBySearch(String searchInfo) {
	
		return engineerMapper.selectBySearch(searchInfo);
	}

	@Override
	public long insertAdmin(Admin admin) {
		
		Date date = new Date(new java.util.Date().getTime());
		admin.setGmtCreate(date);
		admin.setGmtModified(date);
		
		//密码加密后存入数据库
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		return adminMapper.insert(admin)>0?admin.getId():0;
	}

	@Override
	public long updateAdmin(Admin admin) {
		//更改修改的日期
		//日期，注意Date用java.sql.Date，以防止前台验证报错 Validation failed for object
		Date date = new Date(new java.util.Date().getTime());
		admin.setGmtModified(date);
		//密码加密后存入数据库
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		return adminMapper.updateByPrimaryKey(admin)>0?1:0;
	}

	@Override
	public List<Admin> selectAllAdmin() {
		
		return adminMapper.selectAll();
	}

	@Override
	public long deleteAdminById(long id) {
		
		return adminMapper.deleteByPrimaryKey(id)>0?1:0;
	}

	@Override
	public Admin selectAdminById(long id) {
		
		return adminMapper.selectByPrimaryKey(id);
	}

}
