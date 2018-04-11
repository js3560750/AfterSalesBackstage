package com.jinsong.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jinsong.mapper.AdminMapper;
import com.jinsong.mapper.EngineerMapper;
import com.jinsong.mapper.ErrorMapper;
import com.jinsong.mapper.FactoryMapper;
import com.jinsong.mapper.InstallMapper;
import com.jinsong.mapper.MaintainMapper;
import com.jinsong.mapper.ProductMapper;
import com.jinsong.mapper.RepairMapper;
import com.jinsong.model.Admin;
import com.jinsong.model.Engineer;
import com.jinsong.model.Error;
import com.jinsong.model.Factory;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Product;
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

	@Autowired
	ProductMapper productMapper;

	@Autowired
	ErrorMapper errorMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AdminMapper adminMapper;

	@Override
	public long companyLogin(String account, String password) {
		try {
			Admin admin = adminMapper.selectByAccount(account);
			if (!passwordEncoder.matches(password, admin.getPassword())) {
				// 密码不对
				return -1;
			} else if (!admin.getAuthority().equals("ROLE_ADMIN")) {// String类型的比较要用equals！！！！！String类型==比较的是地址
				// 权限不对
				return -2;
			} else {
				// 都对了
				return 1;
			}
		} catch (Exception e) {
			// 用户名错误
			return -3;
		}

	}

	@Override
	public long engineerLogin(String account, String password) {
		try {
			Engineer engineer = engineerMapper.selectByAccount(account);
			if (!engineer.getPassword().equals(password)) {
				// 密码不对
				return -1;
			} else {
				// 都对了
				return 1;
			}
		} catch (Exception e) {
			// 用户名错误
			return -3;
		}

	}

	@Override
	public long insertRepair(Repair repair) {
		// 日期
		Date date = new Date();
		repair.setApplyTime(date);
		repair.setGmtCreate(date);
		repair.setGmtModified(date);
		repair.setDealTime(date);

		// 获取当前时间后2天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +2);// 当前时间+2天
		date = calendar.getTime();
		repair.setEstimatedArrivalTime(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "wx" + str + num;
		repair.setOrderNumber(orderNumber);

		// 订单状态
		repair.setStatus("已受理");

		// 工程师电话
		String engineerName = repair.getEngineer();
		Engineer engineer = engineerMapper.selectByName(engineerName);
		repair.setEngineerTel(engineer.getTel());

		return repairMapper.insert(repair) > 0 ? repair.getId() : 0;
	}

	@Override
	public long insertMaintain(Maintain maintain) {
		// 日期
		Date date = new Date();
		maintain.setOrderTime(date);
		maintain.setGmtCreate(date);
		maintain.setGmtModified(date);

		// 获取当前时间后2天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +2);// 当前时间+2天
		date = calendar.getTime();
		maintain.setEstimatedArrivalTime(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "by" + str + num;
		maintain.setOrderNumber(orderNumber);

		// 订单状态
		maintain.setStatus("已受理");

		// 工程师电话
		String engineerName = maintain.getEngineer();
		Engineer engineer = engineerMapper.selectByName(engineerName);
		maintain.setEngineerTel(engineer.getTel());

		return maintainMapper.insert(maintain) > 0 ? maintain.getId() : 0;
	}

	@Override
	public long insertInstall(Install install) {
		// 日期
		Date date = new Date();
		install.setOrderTime(date);
		install.setGmtCreate(date);
		install.setGmtModified(date);

		// 获取当前时间后2天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +2);// 当前时间+2天
		date = calendar.getTime();
		install.setEstimatedArrivalTime(date);

		// 订单号
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		Random random = new Random();
		int num = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数
		String orderNumber = "az" + str + num;
		install.setOrderNumber(orderNumber);

		// 订单状态
		install.setStatus("已受理");

		// 工程师电话
		String engineerName = install.getEngineer();
		Engineer engineer = engineerMapper.selectByName(engineerName);
		install.setEngineerTel(engineer.getTel());

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
	public long updateInstallEngineer(long id, String engineerName) {
		// 从数据库获得工程师电话
		Engineer engineer = engineerMapper.selectByName(engineerName);
		String tel = engineer.getTel();

		// 更新Install的工程师姓名和电话、工单状态
		Install install = installMapper.selectByPrimaryKey(id);
		install.setEngineer(engineerName);
		install.setEngineerTel(tel);
		install.setGmtModified(new Date());

		return installMapper.updateByPrimaryKey(install) > 0 ? install.getId() : 0;
	}

	@Override
	public long updateMaintainEngineer(long id, String engineerName) {
		// 从数据库获得工程师电话
		Engineer engineer = engineerMapper.selectByName(engineerName);
		String tel = engineer.getTel();

		// 更新Maintain的工程师姓名和电话、工单状态
		Maintain maintain = maintainMapper.selectByPrimaryKey(id);
		maintain.setEngineer(engineerName);
		maintain.setEngineerTel(tel);
		maintain.setGmtModified(new Date());

		return maintainMapper.updateByPrimaryKey(maintain) > 0 ? maintain.getId() : 0;
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

		return factoryMapper.selectBySearch(searchInfo);
	}

	@Override
	public long insertProduct(Product product) {
		// 日期
		Date date = new Date();
		product.setGmtCreate(date);
		product.setGmtModified(date);
		product.setShipDate(date);
		product.setStatusChangeTime(date);
		product.setStatusOperator("管理员");

		return productMapper.insert(product) > 0 ? product.getId() : 0;
	}

	@Override
	public long updateProduct(Product product) {
		// 日期
		Date date = new Date();
		product.setGmtModified(date);

		// 更新，直接把新的传进来，会根据主键去更新对应的行
		return productMapper.updateByPrimaryKey(product);
	}

	@Override
	public List<Product> selectAllProduct() {

		return productMapper.selectAll();
	}

	@Override
	public long deleteProductById(long id) {

		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Product selectProductById(long id) {

		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> selectProductBySearch(String searchInfo) {

		return productMapper.selectBySearch(searchInfo);
	}

	@Override
	public long insertError(Error error) {

		return errorMapper.insert(error) > 0 ? error.getId() : 0;
	}

	@Override
	public List<Error> selectAllError() {

		return errorMapper.selectAll();
	}

	@Override
	public long deleteErrorById(long id) {

		return errorMapper.deleteByPrimaryKey(id) > 0 ? 1 : 0;
	}

	@Override
	public List<Repair> selectUnfinishedRepairByEngineer(String account) {

		return repairMapper.selectUnfinishedRepairByEngineer(account);
	}

	@Override
	public List<Install> selectUnfinishedInstallByEngineer(String account) {

		return installMapper.selectUnfinishedInstallByEngineer(account);
	}

	@Override
	public List<Maintain> selectUnfinishedMaintainByEngineer(String account) {

		return maintainMapper.selectUnfinishedMaintainByEngineer(account);
	}

	@Override
	public List<Repair> selectFinishedRepairByEngineer(String account) {

		return repairMapper.selectFinishedRepairByEngineer(account);
	}

	@Override
	public List<Install> selectFinishedInstallByEngineer(String account) {

		return installMapper.selectFinishedInstallByEngineer(account);
	}

	@Override
	public List<Maintain> selectFinishedMaintainByEngineer(String account) {

		return maintainMapper.selectFinishedMaintainByEngineer(account);
	}

	@Override
	public long repairSignIn(long id) {
		Repair repair = repairMapper.selectByPrimaryKey(id);

		Date date = new Date();
		repair.setArrivaledTime(date);
		repair.setStatus("进行中");
		return repairMapper.updateByPrimaryKey(repair) > 0 ? 1 : 0;
	}

	@Override
	public long installSignIn(long id) {
		Install install = installMapper.selectByPrimaryKey(id);

		Date date = new Date();
		install.setArrivaledTime(date);
		install.setStatus("进行中");

		return installMapper.updateByPrimaryKey(install)>0?1:0;
	}

	@Override
	public long maintainSignIn(long id) {
		Maintain maintain = maintainMapper.selectByPrimaryKey(id);
		Date date = new Date();
		maintain.setArrivaledTime(date);
		maintain.setStatus("进行中");
		return maintainMapper.updateByPrimaryKey(maintain)>0?1:0;
	}

	@Override
	public long repairFinish(long id) {
		Repair repair = repairMapper.selectByPrimaryKey(id);

		Date date = new Date();
		repair.setFinishedTime(date);
		repair.setStatus("已完成");
		return repairMapper.updateByPrimaryKey(repair)>0?1:0;
	}

	@Override
	public long installFinish(long id) {
		Install install = installMapper.selectByPrimaryKey(id);

		Date date = new Date();
		install.setFinishedTime(date);
		install.setStatus("已完成");
		return installMapper.updateByPrimaryKey(install)>0?1:0;
	}

	@Override
	public long maintainFinish(long id) {
		Maintain maintain = maintainMapper.selectByPrimaryKey(id);

		Date date = new Date();
		maintain.setFinishedTime(date);
		maintain.setStatus("已完成");
		return maintainMapper.updateByPrimaryKey(maintain)>0?1:0;
	}

	@Override
	public long updateRepair(Map<String, Object> params) {
		String id = params.get("id").toString();
		Repair repair = repairMapper.selectByPrimaryKey(Long.valueOf(id));
		repair.setIsInsuranced(Byte.valueOf(params.get("isInsuranced").toString()));
		repair.setIsReplaceProduct(Byte.valueOf(params.get("isReplaceProduct").toString()));
		repair.setIsReturnFactory(Byte.valueOf(params.get("isReturnFactory").toString()));
		repair.setHospitalName(params.get("hospitalName").toString());
		repair.setHospitalContact(params.get("hospitalContact").toString());
		repair.setHospitalTel(params.get("hospitalTel").toString());
		repair.setProductModel(params.get("productModel").toString());
		repair.setProductName(params.get("productName").toString());
		repair.setSerialNumber(params.get("serialNumber").toString());
		repair.setProductVersion(params.get("productVersion").toString());
		repair.setSampleBoxType(params.get("sampleBoxType").toString());
		repair.setReagentType(params.get("reagentType").toString());
		repair.setPhenomenon(params.get("phenomenon").toString());
		repair.setReason(params.get("reason").toString());
		repair.setSolution(params.get("solution").toString());
		repair.setReplaceNameCode(params.get("replaceNameCode").toString());
		repair.setOriginalNameCode(params.get("originalNameCode").toString());
		repair.setProductCondition(params.get("productCondition").toString());
		repair.setOrderRemarks(params.get("orderRemarks").toString());
		return repairMapper.updateByPrimaryKey(repair)>0?1:0;
	}

	@Override
	public long updateInstall(Install install) {
		
		return installMapper.updateByPrimaryKey(install)>0?1:0;
	}

	@Override
	public long updateMaintain(Maintain maintain) {
		
		return maintainMapper.updateByPrimaryKey(maintain)>0?1:0;
	}

}
