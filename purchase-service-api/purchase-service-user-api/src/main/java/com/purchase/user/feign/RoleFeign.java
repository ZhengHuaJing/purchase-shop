package com.purchase.user.feign;

import com.purchase.common.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/12/26 18:16
 * @Description:
 */
@FeignClient("purchase-service-user")
@RequestMapping("/roles")
public interface RoleFeign {

    @GetMapping("/{username}")
    Result listRoleByUsername(@PathVariable String username);
}
