package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerLoss;
import com.softeem.crm.service.CustomerLossService;
import com.softeem.crm.vo.CustomerLossQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/customer_loss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;


    @RequestMapping("/index")
    private String index() {
        return "customerLoss/customer_loss";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        return customerLossService.queryByParamsForTable(customerLossQuery);
    }

    @RequestMapping("/customerLossesInfoPage")
    public String customerLossesInfoPage(Integer id, Model model) {
        CustomerLoss customerLoss = customerLossService.getById(id);
        model.addAttribute("customerLoss", customerLoss);
        return "customerLoss/customer_loss_info";
    }

    @RequestMapping("/addCustomerLossesInfoPage")
    public String addCustomerLossesInfoPage(Integer id, Model model) {
        CustomerLoss customerLoss = customerLossService.getById(id);
        model.addAttribute("customerLoss", customerLoss);
        return "customerLoss/customer_loss_add";
    }

    @RequestMapping("/updateLossById")
    public String updateLossById(Integer id, Model model) {
        CustomerLoss customerLoss = customerLossService.getById(id);
        model.addAttribute("customerLoss", customerLoss);
        return "customerLoss/customer_loss_update";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo updateCusLoss(CustomerLoss customerLoss) {
        customerLossService.updateCusLoss(customerLoss);
        return success("确认流失成功成功!");
    }
}