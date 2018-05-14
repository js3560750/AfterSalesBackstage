package com.jinsong.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Author   Jinsong
 * Email    188949420@qq.com
 * Date     2018/5/11
 *
 * 忽略上面not found in claspath的提示，本类可以正常使用
 *
 * resource.properties里的值映射到Resource类里面
 * 静态资源映射到实体类
 *
 * https://www.imooc.com/video/16718
 *
 */
@Configuration
@ConfigurationProperties(prefix = "com.jinsong.opensource")
@PropertySource(value = "classpath:resource.properties")
public class Resource {
    private String name;
    private String website;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
