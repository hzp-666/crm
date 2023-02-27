package com.softeem.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.pojo.Permission;

import java.util.List;

/**
 * @author hzp
 * @description 针对表【t_permission】的数据库操作Service
 * @createDate 2022-12-27 14:22:57
 */
public interface PermissionService extends IService<Permission> {
    List<String> queryUserHasRolesHasPermissions(Integer userId);

}
