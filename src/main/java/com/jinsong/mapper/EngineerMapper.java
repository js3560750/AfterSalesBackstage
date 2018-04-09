package com.jinsong.mapper;

import com.jinsong.model.Engineer;
import com.jinsong.model.Factory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EngineerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table engineer
     *
     * @mbg.generated Thu Feb 08 10:37:57 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table engineer
     *
     * @mbg.generated Thu Feb 08 10:37:57 CST 2018
     */
    int insert(Engineer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table engineer
     *
     * @mbg.generated Thu Feb 08 10:37:57 CST 2018
     */
    Engineer selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table engineer
     *
     * @mbg.generated Thu Feb 08 10:37:57 CST 2018
     */
    List<Engineer> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table engineer
     *
     * @mbg.generated Thu Feb 08 10:37:57 CST 2018
     */
    int updateByPrimaryKey(Engineer record);
    
    /**
     * 根据姓名获得工程师，所以数据库里工程师姓名不能同名，在输入的时候要进行判定和限制
     * @return
     */
    Engineer selectByName(String engineer);
    
    /**
     * 通过姓名来搜索engineer
     */
    List<Engineer> selectBySearch(String searchInfo);
}