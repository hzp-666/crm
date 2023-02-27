package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.PermissionMapper;
import com.softeem.crm.pojo.Permission;
import com.softeem.crm.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzp
 * @description 针对表【t_permission】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:57
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Override
    public List<String> queryUserHasRolesHasPermissions(Integer userId) {
        return baseMapper.queryUserHasRolesHasPermissions(userId);

    }
}




