package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.ModuleMapper;
import com.softeem.crm.mapper.PermissionMapper;
import com.softeem.crm.pojo.Module;
import com.softeem.crm.pojo.Permission;
import com.softeem.crm.service.ModuleService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.TreeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @description 针对表【t_module】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:57
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module>
        implements ModuleService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<TreeDto> queryAllModules() {
        return this.baseMapper.queryAllModules();
    }

    @Override
    public List<TreeDto> queryAllModules02(Integer roleId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllModules();
        // 根据角色id 查询角色拥有的菜单id  List<Integer>
        LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(roleId != null, Permission::getRoleId, roleId).
                select(Permission::getModuleId);
        List<Object> roleHasMids = permissionMapper.selectObjs(lambdaQueryWrapper);
        if (null != roleHasMids && roleHasMids.size() > 0) {
            treeDtos.forEach(treeDto -> {
                if (roleHasMids.contains(treeDto.getId())) {
                    //  说明当前角色 分配了该菜单
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }

    @Override
    public Map<String, Object> moduleList() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Module> modules = this.baseMapper.selectList(null);
        result.put("count", modules.size());
        result.put("data", modules);
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveModule(Module module) {
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "请输入菜单名!");
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        LambdaQueryWrapper<Module> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Module::getGrade, module.getGrade()).
                eq(Module::getModuleName, module.getModuleName());
        AssertUtil.isTrue(null != this.baseMapper.selectOne(lambdaQueryWrapper), "该层级下菜单重复!");
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "请指定二级菜单url值");
            lambdaQueryWrapper.clear();//清一下上面的查询条件
            lambdaQueryWrapper.eq(Module::getGrade, module.getGrade()).
                    eq(Module::getUrl, module.getUrl());
            AssertUtil.isTrue(null != this.baseMapper.selectOne(lambdaQueryWrapper), "二级菜单url不可重复!");
        }
        if (grade != 0) {
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(null == parentId || null == this.getById(parentId), "请指定上级菜单!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "请输入权限码!");
        lambdaQueryWrapper.clear();//清一下上面的查询条件
        lambdaQueryWrapper.eq(Module::getOptValue, module.getOptValue());
        AssertUtil.isTrue(null != this.baseMapper.selectOne(lambdaQueryWrapper), "权限码重复!");

        module.setIsValid(0);
        AssertUtil.isTrue(!save(module), "菜单添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {
        AssertUtil.isTrue(null == module.getId() || null == getById(module.getId()), "待更新记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "请指定菜单名称!");
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        LambdaQueryWrapper<Module> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Module::getGrade, module.getGrade()).
                eq(Module::getModuleName, module.getModuleName());
        Module temp = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (null != temp) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该层级下菜单已存在!");
        }

        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "请指定二级菜单url值");
            lambdaQueryWrapper.clear();//清一下上面的查询条件
            lambdaQueryWrapper.eq(Module::getGrade, module.getGrade()).
                    eq(Module::getUrl, module.getUrl());
            temp = this.baseMapper.selectOne(lambdaQueryWrapper);
            if (null != temp) {
                AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该层级下url已存在!");
            }
        }

        if (grade != 0) {
            Integer parentId = module.getParentId();
            AssertUtil.isTrue(null == parentId || null == getById(parentId), "请指定上级菜单!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "请输入权限码!");
        lambdaQueryWrapper.clear();//清一下上面的查询条件
        lambdaQueryWrapper.eq(Module::getOptValue, module.getOptValue());
        temp = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (null != temp) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "权限码已存在!");
        }
        AssertUtil.isTrue(!updateById(module), "菜单更新失败!");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleById(Integer mid) {
        Module temp = getById(mid);
        AssertUtil.isTrue(null == mid || null == temp, "待删除记录不存在!");
        /**
         * 如果存在子菜单 不允许删除
         */
        LambdaQueryWrapper<Module> moduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        moduleLambdaQueryWrapper.eq(Module::getParentId, mid);
        int count = this.baseMapper.selectCount(moduleLambdaQueryWrapper).intValue();
        AssertUtil.isTrue(count > 0, "存在子菜单，不支持删除操作!");

        //  权限表
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        permissionLambdaQueryWrapper.eq(Permission::getModuleId, mid);
        count = permissionMapper.selectCount(permissionLambdaQueryWrapper).intValue();
        if (count > 0) {
            AssertUtil.isTrue(permissionMapper.delete(permissionLambdaQueryWrapper) < count, "菜单删除失败!");
        }
//        temp.setIsValid(1);
        AssertUtil.isTrue(this.baseMapper.deleteById(mid) < 1, "菜单删除失败!");

    }

    @Override
    public List<Map<String, Object>> queryAllModulesByGrade(Integer grade) {
        LambdaQueryWrapper<Module> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return null;
    }
}




