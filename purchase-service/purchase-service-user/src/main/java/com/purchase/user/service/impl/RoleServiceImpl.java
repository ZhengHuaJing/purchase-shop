package com.purchase.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.user.entity.Permission;
import com.purchase.user.entity.Role;
import com.purchase.user.entity.RolePermission;
import com.purchase.user.entity.UserRole;
import com.purchase.user.mapper.RoleMapper;
import com.purchase.user.service.PermissionService;
import com.purchase.user.service.RolePermissionService;
import com.purchase.user.service.RoleService;
import com.purchase.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    @Override
    public Role getByName(String roleName) {
        return getOne(new QueryWrapper<Role>().lambda().eq(Role::getName, roleName));
    }

    @Autowired
    private RoleService roleService;

    /**
     * 为用户添加角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 是否添加成功
     */
    @Override
    public Boolean addRole(Integer userId, Integer roleId) {
        roleId = (roleId == null) ? roleService.getByName("health_manage").getId() : roleId;

        // 判断是否已存在
        Map<SFunction<UserRole, ?>, Integer> map = new HashMap<>();
        map.put(UserRole::getRoleId, roleId);
        map.put(UserRole::getUserId, userId);
        if (userRoleService.getOne(new QueryWrapper<UserRole>().lambda().allEq(map)) != null) {
            return true;
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        return userRoleService.save(userRole);
    }

    /**
     * 根据用户 ID 查询角色
     *
     * @param userId 主键ID
     * @return 角色列表
     */
    @Override
    public List<Role> listRoleById(Integer userId) {
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId));
        List<Integer> roleIds = new ArrayList<>(userRoles.size());

        userRoles.forEach(userRole -> roleIds.add(userRole.getRoleId()));

        return (List<Role>) roleService.listByIds(roleIds);
    }

    @Autowired
    private PermissionService permissionService;

    /**
     * 根据角色 ID 查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public List<Permission> listPermissionByRoleId(Integer roleId) {
        List<RolePermission> rolePermissions = rolePermissionService.list(new QueryWrapper<RolePermission>().lambda()
                .eq(RolePermission::getRoleId, roleId));

        // 该角色不在表中
        if (rolePermissions.size() == 0) {
            return new ArrayList<>();
        }

        List<Integer> permissionIds = new ArrayList<>();
        rolePermissions.forEach(rolePermission -> permissionIds.add(rolePermission.getPermissionId()));

        return (List<Permission>) permissionService.listByIds(permissionIds);
    }
}
