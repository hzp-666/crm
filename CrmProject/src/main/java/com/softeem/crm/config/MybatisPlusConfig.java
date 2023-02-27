package com.softeem.crm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@MapperScan("org.hzp.mybatisplus.mapper")//可以将主类的注解移到此处
public class MybatisPlusConfig {
    //<bean id='mybatisPlusInterceptor' class='com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor'/>
    //添加分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁
        return interceptor;
    }

}
