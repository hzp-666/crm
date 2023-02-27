package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.SaleChance;
import com.softeem.crm.vo.SaleChanceQuery;

import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_sale_chance】的数据库操作Service
 * @createDate 2022-12-27 14:22:57
 */
public interface SaleChanceService extends IService<SaleChance> {
    public Map<String, Object> queryByParamsForTable(SaleChanceQuery saleChanceQuery);

    public void saveSaleChance(SaleChance saleChance);

    public void updateSaleChance(SaleChance saleChance);

    public void deleteSaleChancesByIds(Integer[] ids);

    public void updateSaleChanceDevResult(Integer id, Integer devResult);
}
