package com.softeem.crm.config;

import com.softeem.crm.interceptor.NoLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private NoLoginInterceptor noLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(noLoginInterceptor)
                .addPathPatterns("/**")//所有请求都会拦截，但是以下路径可以放行
                .excludePathPatterns("/user/login", "/index", "/css/**", "/js/**", "/images/**", "/lib/**");
    }
}