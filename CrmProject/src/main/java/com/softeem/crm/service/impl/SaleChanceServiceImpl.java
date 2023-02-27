package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.enums.DevResult;
import com.softeem.crm.enums.StateStatus;
import com.softeem.crm.mapper.SaleChanceMapper;
import com.softeem.crm.mapper.UserMapper;
import com.softeem.crm.pojo.SaleChance;
import com.softeem.crm.pojo.User;
import com.softeem.crm.service.SaleChanceService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.utils.PhoneUtil;
import com.softeem.crm.vo.SaleChanceQuery;
import com.softeem.crm.vo.SaleChanceVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hzp
 * @description 针对表【t_sale_chance】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:57
 */
@Service
public class SaleChanceServiceImpl extends ServiceImpl<SaleChanceMapper, SaleChance>
        implements SaleChanceService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryByParamsForTable(SaleChanceQuery saleChanceQuery) {
        Page<SaleChance> pageParam = new Page<>(saleChanceQuery.getPage(), saleChanceQuery.getLimit());

        LambdaQueryWrapper<SaleChance> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.
                like(StringUtils.isNotBlank(saleChanceQuery.getCustomerName()),
                        SaleChance::getCustomerName, "%" + saleChanceQuery.getCustomerName() + "%").
                eq(StringUtils.isNotBlank(saleChanceQuery.getCreateMan()),
                        SaleChance::getCreateMan, saleChanceQuery.getCreateMan()).
                eq(StringUtils.isNotBlank(saleChanceQuery.getState()), SaleChance::getState, saleChanceQuery.getState()).
                eq(StringUtils.isNotBlank(saleChanceQuery.getAssignMan()), SaleChance::getAssignMan, saleChanceQuery.getAssignMan()).
                eq(saleChanceQuery.getDevResult() != null, SaleChance::getDevResult, saleChanceQuery.getDevResult()).
                orderByDesc(SaleChance::getId);

        Page<SaleChance> page = this.baseMapper.selectPage(pageParam, lambdaQueryWrapper);

        List<SaleChance> saleChanceList = page.getRecords();

        List<SaleChanceVo> saleChanceVoList = new ArrayList<>();
        //将SaleChance里面的数据都转存到SaleChanceVo里面
        for (SaleChance saleChance : saleChanceList) {
            SaleChanceVo saleChanceVo = new SaleChanceVo();
            //将原来的bean对象中的数据赋值到vo对象
            BeanUtils.copyProperties(saleChance, saleChanceVo);
            User user = userMapper.selectById(saleChance.getAssignMan());
            if (user != null) {
                saleChanceVo.setUname(user.getUserName());
            }
            saleChanceVoList.add(saleChanceVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", page.getTotal());
        result.put("data", saleChanceVoList);
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Transactional
    @Override
    public void saveSaleChance(SaleChance saleChance) {
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getStatus());
        if (StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            saleChance.setAssignTime(new Date());
        }
        saleChance.setIsValid(0);
        AssertUtil.isTrue(!this.save(saleChance), "机会数据添加失败！");
    }

    @Override
    @Transactional
    public void updateSaleChance(SaleChance saleChance) {
        AssertUtil.isTrue(null == saleChance.getId(), "待更新记录不存在!");
        //通过机会数据的主键id查询出机会数据，赋值给temp对象
        SaleChance temp = getById(saleChance.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在!");
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        //数据库里面的字段为null并且用户提交上来的指派人不为空
        if (StringUtils.isBlank(temp.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            //数据库里面的字段指派人不为空 并且 用户提交上来的指派人为null
        } else if (StringUtils.isNotBlank(temp.getAssignMan()) && StringUtils.isBlank(saleChance.getAssignMan())) {
            saleChance.setAssignMan("");
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
        } else if (StringUtils.isNotBlank(temp.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan())) {
            if (!temp.getAssignMan().equals(saleChance.getAssignMan())) {
                saleChance.setAssignTime(new Date());
            }
        }

        int update = this.baseMapper.update(saleChance, Wrappers.<SaleChance>lambdaUpdate().
                set(SaleChance::getAssignTime, saleChance.getAssignTime()).
                eq(SaleChance::getId, saleChance.getId()));
        //saleChance.setUpdateDate(new Date());//mybatis plus的的自动填充
        AssertUtil.isTrue(update < 1, "机会数据更新失败!");
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteSaleChancesByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的机会数据!");
        AssertUtil.isTrue(!removeBatchByIds(Arrays.asList(ids)), "机会数据删除失败!");
    }

    @Override
    @Transactional
    public void updateSaleChanceDevResult(Integer id, Integer devResult) {
        AssertUtil.isTrue(null == id, "待更新记录不存在!");
        SaleChance temp = getById(id);
        AssertUtil.isTrue(null == temp, "待更新记录不存在!");
        temp.setDevResult(devResult);
        temp.setUpdateDate(null);
        //因为temp有updateTime，不会自己更新更新时间，设置为null之后才会将更新时间设置为操作的时间
        AssertUtil.isTrue(!this.updateById(temp), "机会数据更新失败!");
    }

    /*
      表单基本参数校验
    */
    private void checkParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "请输入客户名!");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "请输入联系电话!");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(linkPhone)), "手机号格式不合法!");
    }
}




