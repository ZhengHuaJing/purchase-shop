package com.purchase.purchaseservicegoods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.purchaseservicegoods.mapper.CategoryMapper;
import com.purchase.purchaseservicegoods.service.CategoryService;
import com.purchase.purchaseservicegoodsapi.entity.Category;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
