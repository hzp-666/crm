package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.CustomerLinkman;
import com.softeem.crm.vo.CustomerLinkQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_linkman】的数据库操作Service
 * @createDate 2022-12-27 14:22:56
 */
public interface CustomerLinkmanService extends IService<CustomerLinkman> {

    Map<String, Object> queryByParamsForTable(CustomerLinkQuery customerLinkQuery);

    void saveOrUpdateCusLinkMan(CustomerLinkman customerLinkman);
}
