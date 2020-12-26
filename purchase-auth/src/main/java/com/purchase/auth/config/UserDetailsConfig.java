package com.purchase.auth.config;

import com.purchase.common.pojo.Result;
import com.purchase.user.feign.RoleFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/10/27 14:25
 * @Description:
 */
@Slf4j
@Component
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    private RoleFeign roleFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result response = roleFeign.listRoleByUsername(username);
        if (response.getCode() != 200) {
            throw new UsernameNotFoundException(response.getMsg());
        }

        Map data = (Map) response.getData();
        String password = (String) data.get("password");
        List<Map> authorities = (List<Map>) data.get("authorities");
        List<SimpleGrantedAuthority> collect = authorities.stream()
                .map(a -> new SimpleGrantedAuthority((String) a.get("authority")))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, password, collect);
    }
}
