package com.purchase.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.user.entity.Address;
import com.purchase.user.mapper.AddressMapper;
import com.purchase.user.service.AddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
