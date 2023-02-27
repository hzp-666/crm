package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.CustomerServe;
import com.softeem.crm.vo.CustomerServeQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_serve】的数据库操作Service
 * @createDate 2022-12-27 14:22:56
 */
public interface CustomerServeService extends IService<CustomerServe> {

    Map<String, Object> queryByParamsForTable(CustomerServeQuery customerServeQuery);

    void saveOrUpdateCustomerServe(CustomerServe customerServe);
}
