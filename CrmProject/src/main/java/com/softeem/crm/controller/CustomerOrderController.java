package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.service.CustomerOrderService;
import com.softeem.crm.vo.CustomerOrderQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class CustomerOrderController extends BaseController {

    @Resource
    private CustomerOrderService customerOrderService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerOrdersByParams(CustomerOrderQuery customerOrderQuery) {
        return customerOrderService.queryByParamsForTable(customerOrderQuery);
    }

   
}
