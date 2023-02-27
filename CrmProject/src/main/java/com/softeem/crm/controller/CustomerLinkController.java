package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerLinkman;
import com.softeem.crm.service.CustomerLinkmanService;
import com.softeem.crm.vo.CustomerLinkQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/link")
public class CustomerLinkController extends BaseController {
    @Resource
    private CustomerLinkmanService customerLinkmanService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CustomerLinkQuery customerLinkQuery) {
        return customerLinkmanService.queryByParamsForTable(customerLinkQuery);
    }

    @RequestMapping("addOrUpdateCusLinkManPage")
    public String addOrUpdateCusDevPlanPage(Integer cusId, Integer id, Model model) {
        model.addAttribute("linkMan", customerLinkmanService.getById(id));
        model.addAttribute("cusId", cusId);
        return "customer/cus_linkman_add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlan(CustomerLinkman customerLinkman) {
        customerLinkmanService.saveOrUpdateCusLinkMan(customerLinkman);
        return success("计划项添加成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        customerLinkmanService.removeById(id);
        return success("计划项删除成功!");
    }
}
