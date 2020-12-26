package com.purchase.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.goods.mapper.SkuMapper;
import com.purchase.goods.service.SkuService;
import com.purchase.purchaseservicegoodsapi.entity.Sku;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-25
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

}
