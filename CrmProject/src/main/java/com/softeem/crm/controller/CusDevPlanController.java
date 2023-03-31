package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CusDevPlan;
import com.softeem.crm.service.CusDevPlanService;
import com.softeem.crm.service.SaleChanceService;
import com.softeem.crm.vo.CusDevPlanQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/cus_dev_plan")
@Slf4j
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;


    /*
      客户开发主页面
    */
    @RequestMapping("/index")
    public String index() {
        log.info("修改11111111111111111");
        log.info("修改22222222222222222");
        log.info("修改33333333333333333");
        return "cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model) {
        model.addAttribute("saleChance", saleChanceService.getById(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery) {
        return cusDevPlanService.queryByParamsForTable(cusDevPlanQuery);
    }

    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid, Integer id, Model model) {
        model.addAttribute("cusDevPlan", cusDevPlanService.getById(id));
        model.addAttribute("sid", sid);
        return "cusDevPlan/add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.saveOrUpdateCusDevPlan(cusDevPlan);
        return success("计划项添加成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        cusDevPlanService.removeById(id);
        return success("计划项删除成功!");
    }
}
