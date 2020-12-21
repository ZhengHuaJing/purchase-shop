package com.purchase.purchasegoods.service.impl;

import com.purchase.purchasegoods.entity.Category;
import com.purchase.purchasegoods.mapper.CategoryMapper;
import com.purchase.purchasegoods.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
