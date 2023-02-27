package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.CustomerOrderMapper;
import com.softeem.crm.pojo.CustomerOrder;
import com.softeem.crm.service.CustomerOrderService;
import com.softeem.crm.vo.CustomerOrderQuery;
import com.softeem.crm.vo.CustomerOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_customer_order】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:56
 */
@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder>
        implements CustomerOrderService {

    @Override
    public Map<String, Object> queryByParamsForTable(CustomerOrderQuery customerOrderQuery) {

        Page<CustomerOrder> page = new Page<>(customerOrderQuery.getPage(), customerOrderQuery.getLimit());

        LambdaQueryWrapper<CustomerOrder> lambdaQueryWrapper = Wrappers.<CustomerOrder>lambdaQuery().
                eq(CustomerOrder::getCusId, customerOrderQuery.getCusId()).
                eq(StringUtils.isNotBlank(customerOrderQuery.getOrderNo()), CustomerOrder::getOrderNo, customerOrderQuery.getOrderNo()).
                eq(customerOrderQuery.getState() != null, CustomerOrder::getState, customerOrderQuery.getState());

        Page<CustomerOrder> customerOrderPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        Map<String, Object> result = new HashMap<>();
        result.put("count", customerOrderPage.getTotal());
        result.put("data", customerOrderPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public CustomerOrderVo queryOrderDetailByOrderId(Integer orderId) {
        CustomerOrderVo customerOrderVo = this.baseMapper.queryOrderDetailByOrderId(orderId);

        return customerOrderVo;
    }
}




