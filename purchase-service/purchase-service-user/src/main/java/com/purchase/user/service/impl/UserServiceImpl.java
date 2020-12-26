package com.purchase.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.user.entity.User;
import com.purchase.user.mapper.UserMapper;
import com.purchase.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getByUsername(String username) {
        return getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }
}
