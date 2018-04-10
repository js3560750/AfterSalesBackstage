package com.jinsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinsong.model.Engineer;
import com.jinsong.model.Factory;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Product;
import com.jinsong.model.Repair;
import com.jinsong.model.Error;

@Service
public interface CompanyService {
	
	/**
	 * 微信公司端账号登录
	 */
	long companyLogin(String account,String password);

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
     * 更新install工单的工程师信息
     */
    long updateInstallEngineer(long id,String engineer);
    
    /**
     * 更新Maintain工单的工程师信息
     */
    long updateMaintainEngineer(long id,String engineer);
    
    /**
     * 获得所有install工单
     */
	List<Install> selectAllInstall();
	
	/**
	 * 获得所有maintain工单
	 */
	List<Maintain> selectAllMaintain();
	
	/**
	 *根据id获得单个install工单
	 */
	Install selectInstallById(long id);
	
	/**
	 *根据id获得单个maintain工单
	 */
	Maintain selectMaintainById(long id);
	
	/**
	 * 根据id删除repair
	 */
	long deleteRepairById(long id);
	
	/**
	 * 根据id删除install
	 */
	long deleteInstallById(long id);
	
	/**
	 * 根据id删除maintain
	 */
	long deleteMaintainById(long id);
	
	/**
     * 通过hospital_name或者engineer来搜索repair工单
     */
	List<Repair> selectRepairBySearch(String searchInfo);
	
	/**
     * 通过hospital_name或者engineer来搜索install工单
     */
	List<Install> selectInstallBySearch(String searchInfo);
	
	/**
     * 通过hospital_name或者engineer来搜索maintain工单
     */
	List<Maintain> selectMaintainBySearch(String searchInfo);
	
	/**
	 * 添加质检部门登记的设备信息factory
	 */
	long insertFactory(Factory factory);
	
	/**
	 * 根据id修改质检部门登记的设备信息factory
	 */
	long updateFactory(Factory factory);
	
	/**
	 * 获得所有质检部门登记的设备信息factory
	 */
	List<Factory> selectAllFactory();
	
	/**
	 * 根据id删除质检部门登记的设备信息factory
	 */
	long deleteFactoryById(long id);
	
	/**
	 * 根据id获得单个factory
	 */
	Factory selectFactoryById(long id);
	
	/**
     * 通过serial_number来搜索factory
     */
	List<Factory> selectFactoryBySearch(String searchInfo);
	
	/**
	 * 售后部门添加设备信息product
	 */
	long insertProduct(Product product);
	
	/**
	 * 根据id修改质售后部门设备信息product
	 */
	long updateProduct(Product product);
	
	/**
	 * 获得所有售后部门设备信息product
	 */
	List<Product> selectAllProduct();
	
	/**
	 * 根据id删除售后部门设备信息product
	 */
	long deleteProductById(long id);
	
	/**
	 * 根据id获得单个售后部门设备信息product
	 */
	Product selectProductById(long id);
	
	/**
     * 通过serial_number和hospital_name来搜索product
     */
	List<Product> selectProductBySearch(String searchInfo);
	
	
	/**
	 * 添加设备故障信息error
	 */
	long insertError(Error error);
	
	/**
	 * 获得所有error信息
	 */
	List<Error> selectAllError();
	
	/**
	 * 根据id删除单个error信息
	 *
	 */
	long deleteErrorById(long id);
	
}
