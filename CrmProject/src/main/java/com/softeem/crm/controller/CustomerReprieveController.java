package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerReprieve;
import com.softeem.crm.service.CustomerReprieveService;
import com.softeem.crm.vo.CustomerReprieveQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/cus_reprieve")
@Controller
public class CustomerReprieveController extends BaseController {
    @Resource
    private CustomerReprieveService customerReprieveService;


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCusReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {
        return customerReprieveService.queryByParamsForTable(customerReprieveQuery);

    }

    @RequestMapping("addOrUpdateCusReprievePage")
    public String addOrUpdateCusDevPlanPage(Integer lossId, Integer id, Model model) {
        model.addAttribute("reprieve", customerReprieveService.getById(id));
        model.addAttribute("lossId", lossId);
        return "customerLoss/cus_reprieve_add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdateCusReprieve(CustomerReprieve customerReprieve) {
        customerReprieveService.saveOrUpdateCusReprieve(customerReprieve);
        return success("暂缓添加成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        customerReprieveService.removeById(id);
        return success("暂缓删除成功!");
    }
}
