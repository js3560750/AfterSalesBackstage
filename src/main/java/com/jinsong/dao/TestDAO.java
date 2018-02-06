package com.jinsong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jinsong.model.Engineer;

@Mapper
public interface TestDAO {

	/**
	 * 获取所有工程师的信息
	 */
	List<Engineer> listEngineer();
}
