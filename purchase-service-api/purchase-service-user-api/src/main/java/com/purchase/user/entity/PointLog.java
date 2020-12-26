package com.purchase.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_point_log")
public class PointLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String userId;

    private Integer point;


}
