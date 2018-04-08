package com.jinsong.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinsong.mapper.EngineerMapper;
import com.jinsong.mapper.FactoryMapper;
import com.jinsong.mapper.InstallMapper;
import com.jinsong.mapper.MaintainMapper;
import com.jinsong.mapper.RepairMapper;
import com.jinsong.model.Engineer;
import com.jinsong.model.Factory;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	RepairMapper repairMapper;

	@Autowired
	MaintainMapper maintainMapper;

	@Autowired
	InstallMapper installMapper;

	@Autowired
	EngineerMapper engineerMapper;

	@Autowired
	FactoryMapper factoryMapper;

	@Override
	public long insertRepair(Repair repair) {
		// 日期
		Date date = new Date();
		repair.setApplyTime(date);
		repair.setGmtCreate(date);
		repair.setGmtModified(date);
		repair.setDealTime(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "wx" + str + num;
		repair.setOrderNumber(orderNumber);

		// 订单状态
		repair.setStatus("已受理");

		return repairMapper.insert(repair) > 0 ? repair.getId() : 0;
	}

	@Override
	public long insertMaintain(Maintain maintain) {
		// 日期
		Date date = new Date();
		maintain.setOrderTime(date);
		maintain.setGmtCreate(date);
		maintain.setGmtModified(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "by" + str + num;
		maintain.setOrderNumber(orderNumber);

		// 订单状态
		maintain.setStatus("已受理");

		return maintainMapper.insert(maintain) > 0 ? maintain.getId() : 0;
	}

	@Override
	public long insertInstall(Install install) {
		// 日期
		Date date = new Date();
		install.setOrderTime(date);
		install.setGmtCreate(date);
		install.setGmtModified(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "az" + str + num;
		install.setOrderNumber(orderNumber);

		// 订单状态
		install.setStatus("已受理");

		return installMapper.insert(install) > 0 ? install.getId() : 0;
	}

	@Override
	public List<Engineer> listEngineer() {

		return engineerMapper.selectAll();
	}

	@Override
	public List<Repair> selectByStautsNull() {

		return repairMapper.selectByStautsNull();
	}

	@Override
	public List<Repair> selectAll() {

		return repairMapper.selectAll();
	}

	@Override
	public Repair selectById(long id) {

		return repairMapper.selectByPrimaryKey(id);
	}

	@Override
	public long updateRepairEngineer(long id, String engineerName) {
		// 从数据库获得工程师电话
		Engineer engineer = engineerMapper.selectByName(engineerName);
		String tel = engineer.getTel();

		// 更新Repair的工程师姓名和电话、工单状态
		Repair repair = repairMapper.selectByPrimaryKey(id);
		repair.setEngineer(engineerName);
		repair.setEngineerTel(tel);
		repair.setStatus("已受理");
		repair.setGmtModified(new Date());
		repair.setDealTime(new Date());

		return repairMapper.updateByPrimaryKey(repair) > 0 ? repair.getId() : 0;
	}

	@Override
	public List<Install> selectAllInstall() {

		return installMapper.selectAll();
	}

	@Override
	public List<Maintain> selectAllMaintain() {

		return maintainMapper.selectAll();
	}

	@Override
	public Install selectInstallById(long id) {
		// TODO Auto-generated method stub
		return installMapper.selectByPrimaryKey(id);
	}

	@Override
	public Maintain selectMaintainById(long id) {
		// TODO Auto-generated method stub
		return maintainMapper.selectByPrimaryKey(id);
	}

	@Override
	public long deleteRepairById(long id) {
		// TODO Auto-generated method stub
		return repairMapper.deleteByPrimaryKey(id) > 0 ? 1 : 0;
	}

	@Override
	public long deleteInstallById(long id) {
		// TODO Auto-generated method stub
		return installMapper.deleteByPrimaryKey(id) > 0 ? 1 : 0;
	}

	@Override
	public long deleteMaintainById(long id) {
		// TODO Auto-generated method stub
		return maintainMapper.deleteByPrimaryKey(id) > 0 ? 1 : 0;
	}

	@Override
	public List<Repair> selectRepairBySearch(String searchInfo) {
		// TODO Auto-generated method stub
		return repairMapper.selectBySearch(searchInfo);
	}

	@Override
	public List<Install> selectInstallBySearch(String searchInfo) {
		// TODO Auto-generated method stub
		return installMapper.selectBySearch(searchInfo);
	}

	@Override
	public List<Maintain> selectMaintainBySearch(String searchInfo) {
		// TODO Auto-generated method stub
		return maintainMapper.selectBySearch(searchInfo);
	}

	@Override
	public long insertFactory(Factory factory) {

		// 日期
		Date date = new Date();
		factory.setGmtCreate(date);
		factory.setGmtModified(date);
		factory.setTime(date);

		return factoryMapper.insert(factory) > 0 ? factory.getId() : 0;
	}

	@Override
	public long updateFactory(Factory factory) {

		// 日期
		Date date = new Date();
		factory.setGmtModified(date);

		// 更新，直接把新的传进来，会根据主键去更新对应的行
		return factoryMapper.updateByPrimaryKey(factory);
	}

	@Override
	public List<Factory> selectAllFactory() {

		return factoryMapper.selectAll();
	}

	@Override
	public long deleteFactoryById(long id) {

		return factoryMapper.deleteByPrimaryKey(id) > 0 ? 1 : 0;
	}

	@Override
	public Factory selectFactoryById(long id) {

		return factoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Factory> selectFactoryBySearch(String searchInfo) {
		// TODO Auto-generated method stub
		return factoryMapper.selectBySearch(searchInfo);
	}

}
