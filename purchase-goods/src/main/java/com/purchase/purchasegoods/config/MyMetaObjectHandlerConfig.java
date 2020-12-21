package com.purchase.purchasegoods.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充器
 *
 * @author zhenghuajing
 */
@Slf4j
@Component
public class MyMetaObjectHandlerConfig implements MetaObjectHandler {

    /**
     * 插入数据时，自动添加创建时间与更新时间字段
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
    }

    /**
     * 更新数据时，自动修改更新时间字段
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
    }
}
