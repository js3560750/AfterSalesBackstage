package com.jinsong.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Author   Jinsong
 * Email    188949420@qq.com
 * Date     2018/4/26
 */
@RestController
public class TestController2 {


    @GetMapping("/")
    public String test(){

        return "Test ! Moemil AfterSales ~~~~";
    }
}
