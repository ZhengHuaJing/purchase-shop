package com.purchase.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.user.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);
}
