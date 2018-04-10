package com.jinsong.mapper;

import com.jinsong.model.Admin;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Sun Feb 25 11:59:09 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Sun Feb 25 11:59:09 CST 2018
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Sun Feb 25 11:59:09 CST 2018
     */
    Admin selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Sun Feb 25 11:59:09 CST 2018
     */
    List<Admin> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Sun Feb 25 11:59:09 CST 2018
     */
    int updateByPrimaryKey(Admin record);
    
    /**
     * 根据用户名查找
     */
    Admin selectByAccount(String account);
    
    
    
}