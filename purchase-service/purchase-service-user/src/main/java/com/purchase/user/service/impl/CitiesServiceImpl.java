package com.purchase.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.user.entity.Cities;
import com.purchase.user.mapper.CitiesMapper;
import com.purchase.user.service.CitiesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements CitiesService {

}
