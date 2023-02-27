package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerContact;
import com.softeem.crm.service.CustomerContactService;
import com.softeem.crm.vo.CustomerContactQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class CustomerContactController extends BaseController {
    @Resource
    private CustomerContactService customerContactService;


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CustomerContactQuery customerContactQuery) {
        return customerContactService.queryByParamsForTable(customerContactQuery);
    }

    @RequestMapping("addOrUpdateCusContactPage")
    public String addOrUpdateCusDevPlanPage(Integer cusId, Integer id, Model model) {
        model.addAttribute("contact", customerContactService.getById(id));
        model.addAttribute("cusId", cusId);
        return "customer/cus_contact_add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlan(CustomerContact customerContact) {
        customerContactService.saveOrUpdateCusLinkMan(customerContact);
        return success("计划项添加成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        customerContactService.removeById(id);
        return success("计划项删除成功!");
    }
}
