package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.Customer;
import com.softeem.crm.vo.CustomerQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer】的数据库操作Service
 * @createDate 2022-12-27 14:22:56
 */
public interface CustomerService extends IService<Customer> {
    public Map<String, Object> queryByParamsForTable(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(Integer cid);

    public void updateCustomerState();

    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery);

    public Map<String, Object> countCustomerMake();

    public Map<String, Object> countCustomerMake02();
}
