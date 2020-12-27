package com.purchase.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.goods.mapper.BrandMapper;
import com.purchase.goods.service.BrandService;
import com.purchase.goods.entity.Brand;
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
