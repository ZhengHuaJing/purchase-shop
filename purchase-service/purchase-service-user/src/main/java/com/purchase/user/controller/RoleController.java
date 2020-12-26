package com.purchase.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.common.pojo.CodeEnum;
import com.purchase.common.pojo.PageResult;
import com.purchase.common.pojo.Result;
import com.purchase.user.entity.Role;
import com.purchase.user.entity.User;
import com.purchase.user.service.RoleService;
import com.purchase.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @ApiOperation("添加角色")
    @PreAuthorize("hasAuthority('RoleController:addRole')")
    @PostMapping()
    public Result addRole(@RequestBody Role role) {
        return Result.assess(roleService.save(role));
    }

    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('RoleController:deleteRole')")
    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable(name = "id") Integer id) {
        return Result.assess(roleService.removeById(id));
    }

    @ApiOperation("更新角色")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('RoleController:updateRole')")
    @PutMapping("/{id}")
    public Result updateRole(@PathVariable(name = "id") Integer id, @RequestBody Role role) {
        role.setId(id);
        return Result.assess(roleService.updateById(role));
    }

    @ApiOperation("角色详情")
    @ApiImplicitParam(name = "id", value = "角色ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('RoleController:getRole')")
    @GetMapping("/{id}")
    public Result getRole(@PathVariable(name = "id") Integer id) {
        return Result.success(roleService.getById(id));
    }

    @ApiOperation("角色全部数据")
    @PreAuthorize("hasAuthority('RoleController:listRole')")
    @GetMapping()
    public Result listRole() {
        List<Role> list = roleService.list();
        PageResult<Role> rolePageResult = new PageResult<>(list, list.size());
        return Result.success(rolePageResult);
    }

    @ApiOperation("角色分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('RoleController:listRoleQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listRoleQueryPage(@RequestBody Role role, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Role> iPage = roleService.page(new Page<>(page, size), new QueryWrapper<>(role));
        PageResult<Role> rolePageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(rolePageResult);
    }

    @ApiOperation("角色分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('RoleController:listRolePage')")
    @GetMapping("/search/{page}/{size}")
    public Result listRolePage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Role> iPage = roleService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Role> rolePageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(rolePageResult);
    }

    @ApiOperation("角色条件搜索")
    @PreAuthorize("hasAuthority('RoleController:ListRoleQuery')")
    @PostMapping("/search")
    public Result ListRoleQuery(@RequestBody Role role) {
        List<Role> list = roleService.list(new QueryWrapper<>(role));
        PageResult<Role> rolePageResult = new PageResult<>(list, list.size());
        return Result.success(rolePageResult);
    }

    @ApiOperation("获取用户权限列表")
    @GetMapping("/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true)
    public Result listRoleByUsername(@PathVariable String username) {
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        if (user == null) {
            return Result.fail(CodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(), CodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage());
        }

        // 获取用户的权限
        roleService.listRoleById(user.getId()).forEach(role -> {
            roleService.listPermissionByRoleId(role.getId()).forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            });
        });

        map.put("password", user.getPassword());
        map.put("authorities", authorities);

        return Result.success(map);
    }
}
