package com.purchase.purchaseservicegoods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.purchaseservicegoods.mapper.BrandMapper;
import com.purchase.purchaseservicegoods.service.BrandService;
import com.purchase.purchaseservicegoodsapi.entity.Brand;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
