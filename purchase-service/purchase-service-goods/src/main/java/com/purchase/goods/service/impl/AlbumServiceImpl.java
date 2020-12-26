package com.purchase.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.goods.mapper.AlbumMapper;
import com.purchase.goods.service.AlbumService;
import com.purchase.purchaseservicegoodsapi.entity.Album;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

}
