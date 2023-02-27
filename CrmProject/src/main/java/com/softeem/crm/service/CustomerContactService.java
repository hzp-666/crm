package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.CustomerContact;
import com.softeem.crm.vo.CustomerContactQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_contact】的数据库操作Service
 * @createDate 2022-12-27 14:22:56
 */
public interface CustomerContactService extends IService<CustomerContact> {


    Map<String, Object> queryByParamsForTable(CustomerContactQuery customerContactQuery);

    void saveOrUpdateCusLinkMan(CustomerContact customerContact);
}
