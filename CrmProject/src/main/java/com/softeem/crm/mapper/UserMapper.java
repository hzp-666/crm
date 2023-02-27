package com.softeem.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.crm.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:57
 * @Entity com.softeem.crm.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {

    public List<Map> queryAllSales();

}




