package com.jinsong.controller.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.model.Admin;
import com.jinsong.model.Work;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Author   Jinsong
 * Email    188949420@qq.com
 * Date     2018/5/11
 */
@RestController
@RequestMapping("/web")
public class WorkController {

    private static final Logger logger = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    CompanyService companyService;

    /**
     * 跳转到增加售后人员日报记录页面
     */
    @GetMapping("/work")
    public ModelAndView insertWorkView(){
        ModelAndView model = new ModelAndView("work/work_insert");
        return model;
    }

    /**
     * insert售后人员日报记录
     */
    @PostMapping("/work")
    public ModelAndView insertWorkAction(@ModelAttribute Work work){
        try {
            //服务返回>0成功，否则失败
            //但是返回给前端的code=0表示成功
            if (companyService.insertWork(work)>0) {
                return new ModelAndView("work/work_insert_success");
            }
        } catch (Exception e) {
            logger.error("提交设备信息失败" + e.getMessage());
        }
        return new ModelAndView("work/work_insert_fail");
    }

    /**
     * 跳转到查询记录页面，
     */
    @GetMapping("/work/date")
    public ModelAndView selectWorkDateView(){
        ModelAndView model = new ModelAndView("work/work_date");
        return model;
    }

    /**
     * 根据日期显示记录
     */
    @PostMapping("/work/list")
    public ModelAndView selectWorkDateAction(@RequestParam("attendanceTime") String attendanceTime){


        List<Work> list = companyService.selectAllByAttendanceTime(attendanceTime);


        if(list.size()!=0){
            ModelAndView model = new ModelAndView("work/work_all");
            model.addObject("list",list);
            return model;
        }else{
            ModelAndView model = new ModelAndView("work/work_all_fail");
            return model;
        }

    }

    /**
     * 根据id删除work
     */
    @DeleteMapping("/work/{id}")
    public String deleteWorkById(@PathVariable("id") long id) {
        try {
            if(companyService.deleteWorkById(id)>0) {
                return JsUtil.getJSONString(0, "工作记录删除成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return JsUtil.getJSONString(1, "删除失败");
    }
}
