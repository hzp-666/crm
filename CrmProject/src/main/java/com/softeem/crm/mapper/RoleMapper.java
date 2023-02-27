package com.softeem.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.crm.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_role】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:57
 * @Entity com.softeem.crm.pojo.Role
 */
@SuppressWarnings("all")
public interface RoleMapper extends BaseMapper<Role> {
    public List<Map> queryAllRoles(Integer userId);

}




