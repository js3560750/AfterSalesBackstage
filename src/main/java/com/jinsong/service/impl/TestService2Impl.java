package com.jinsong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinsong.dao.TestDAO;
import com.jinsong.model.Engineer;
import com.jinsong.service.TestService2;

@Service
public class TestService2Impl implements TestService2 {
	
	@Autowired
	TestDAO testDAO;

	@Override
	public List<Engineer> listEngineer() {
		// TODO Auto-generated method stub
		return testDAO.listEngineer();
	}

}
