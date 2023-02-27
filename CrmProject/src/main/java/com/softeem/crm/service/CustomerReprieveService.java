package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.CustomerReprieve;
import com.softeem.crm.vo.CustomerReprieveQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_reprieve】的数据库操作Service
 * @createDate 2022-12-27 14:22:56
 */
public interface CustomerReprieveService extends IService<CustomerReprieve> {

    Map<String, Object> queryByParamsForTable(CustomerReprieveQuery customerReprieveQuery);

    void saveOrUpdateCusReprieve(CustomerReprieve customerReprieve);
}
