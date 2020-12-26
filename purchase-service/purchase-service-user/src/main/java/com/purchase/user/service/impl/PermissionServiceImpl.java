package com.purchase.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.user.entity.Permission;
import com.purchase.user.mapper.PermissionMapper;
import com.purchase.user.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
