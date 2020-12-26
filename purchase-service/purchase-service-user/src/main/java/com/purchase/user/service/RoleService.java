package com.purchase.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.user.entity.Permission;
import com.purchase.user.entity.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    Role getByName(String roleName);

    /**
     * 为用户添加角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 是否添加成功
     */
    Boolean addRole(Integer userId, Integer roleId);

    /**
     * 根据用户 ID 查询角色
     *
     * @param userId 主键ID
     * @return 角色列表
     */
    List<Role> listRoleById(Integer userId);

    /**
     * 根据角色 ID 查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<Permission> listPermissionByRoleId(Integer roleId);
}
