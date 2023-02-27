package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.CustomerContactMapper;
import com.softeem.crm.pojo.CustomerContact;
import com.softeem.crm.service.CustomerContactService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerContactQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_contact】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:56
 */
@Service
public class CustomerContactServiceImpl extends ServiceImpl<CustomerContactMapper, CustomerContact>
        implements CustomerContactService {

    @Override
    public Map<String, Object> queryByParamsForTable(CustomerContactQuery customerContactQuery) {
        Page<CustomerContact> page = new Page<>(customerContactQuery.getPage(), customerContactQuery.getLimit());

        LambdaQueryWrapper<CustomerContact> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(customerContactQuery.getCusId() != null,
                CustomerContact::getCusId, customerContactQuery.getCusId());

        Page<CustomerContact> customerContactPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("count", customerContactPage.getTotal());
        result.put("data", customerContactPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void saveOrUpdateCusLinkMan(CustomerContact customerContact) {
        AssertUtil.isTrue(!this.saveOrUpdate(customerContact), "客户往来关系操作失败!");

    }
}




