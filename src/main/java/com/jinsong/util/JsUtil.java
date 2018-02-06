package com.jinsong.util;

import java.io.File;
import java.io.FileOutputStream;

import com.alibaba.fastjson.JSONObject;

/**
 * 工具包
 * 
 * @author 188949420@qq.com
 *
 */
public class JsUtil {

	/**
	 * 有关JSON的工具
	 */
	public static String getJSONString(int code) {
		// 在pom.xml中导入的阿里巴巴JSON开发包
		JSONObject json = new JSONObject(); // 在pom.xml中导入的阿里巴巴JSON开发包！！！！！！！！！！！！！！！！！！！！！
		// 存入键值对
		json.put("code", code);
		// 返回成JSON格式的字符串
		return json.toJSONString();

	}

	public static String getJSONString(int code, String msg) {
		// 在pom.xml中导入的阿里巴巴JSON开发包
		JSONObject json = new JSONObject();
		// 存入键值对
		json.put("code", code);
		json.put("msg", msg);
		// 返回成JSON格式的字符串
		return json.toJSONString();

	}

}
