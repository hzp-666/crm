package com.softeem.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.softeem.crm.pojo.CustomerServe;
import com.softeem.crm.vo.CustomerServeQuery;
import com.softeem.crm.vo.CustomerServeVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author hzp
 * @description 针对表【t_customer_serve】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:56
 * @Entity com.softeem.crm.pojo.CustomerServe
 */
public interface CustomerServeMapper extends BaseMapper<CustomerServe> {

    public IPage<CustomerServeVo> selectByParams(IPage<CustomerServe> page, @Param("customerServeQuery") CustomerServeQuery customerServeQuery);
}




