package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.CustomerLinkmanMapper;
import com.softeem.crm.pojo.CustomerLinkman;
import com.softeem.crm.service.CustomerLinkmanService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerLinkQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_linkman】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:56
 */
@Service
public class CustomerLinkmanServiceImpl extends ServiceImpl<CustomerLinkmanMapper, CustomerLinkman>
        implements CustomerLinkmanService {


    @Override
    public Map<String, Object> queryByParamsForTable(CustomerLinkQuery customerLinkQuery) {
        Page<CustomerLinkman> page = new Page<>(customerLinkQuery.getPage(), customerLinkQuery.getLimit());

        LambdaQueryWrapper<CustomerLinkman> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(customerLinkQuery.getCusId() != null,
                CustomerLinkman::getCusId, customerLinkQuery.getCusId());

        Page<CustomerLinkman> customerLinkmanPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("count", customerLinkmanPage.getTotal());
        result.put("data", customerLinkmanPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void saveOrUpdateCusLinkMan(CustomerLinkman customerLinkman) {
        AssertUtil.isTrue(!this.saveOrUpdate(customerLinkman), "客户联系人操作失败!");
    }
}




