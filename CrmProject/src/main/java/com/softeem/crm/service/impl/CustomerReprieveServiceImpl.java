package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.CustomerReprieveMapper;
import com.softeem.crm.pojo.CustomerReprieve;
import com.softeem.crm.service.CustomerReprieveService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerReprieveQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_reprieve】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:56
 */
@Service
public class CustomerReprieveServiceImpl extends ServiceImpl<CustomerReprieveMapper, CustomerReprieve>
        implements CustomerReprieveService {

    @Override
    public Map<String, Object> queryByParamsForTable(CustomerReprieveQuery customerReprieveQuery) {

        Page<CustomerReprieve> page = new Page<>(customerReprieveQuery.getPage(), customerReprieveQuery.getLimit());

        LambdaQueryWrapper<CustomerReprieve> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(customerReprieveQuery.getLossId() != null, CustomerReprieve::getLossId, customerReprieveQuery.getLossId());

        Page<CustomerReprieve> customerReprievePage = this.baseMapper.selectPage(page, lambdaQueryWrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("count", customerReprievePage.getTotal());
        result.put("data", customerReprievePage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void saveOrUpdateCusReprieve(CustomerReprieve customerReprieve) {
        AssertUtil.isTrue(!this.saveOrUpdate(customerReprieve), "客户联系人操作失败!");
    }
}




