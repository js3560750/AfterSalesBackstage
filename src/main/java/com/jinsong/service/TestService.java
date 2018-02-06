package com.jinsong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinsong.dao.TestDAO;
import com.jinsong.mapper.EngineerMapper;
import com.jinsong.model.Engineer;

/**
 * TestService相当于接口TestService2+其实现类TestService2Impl
 * 相当于省略了个服务层接口，偷懒之举，举个例子说明这样偷懒一样能正常用
 * @author 188949420@qq.com
 *
 */
@Service
public class TestService {
	
	@Autowired
	TestDAO testDAO;
	
	@Autowired
	EngineerMapper engineerMapper;

	/**
	 * 获得所有工程师
	 */
	public List<Engineer> listEngineer(){
		return testDAO.listEngineer();
	}
	
	/**
	 * 获得所有工程师
	 */
	public List<Engineer> listEngineer2(){
		return engineerMapper.selectAll();
	}
	
	/**
	 * 添加工程师
	 */
	public void insertEngineer(Engineer engineer) {
		engineerMapper.insert(engineer);
	}
}
