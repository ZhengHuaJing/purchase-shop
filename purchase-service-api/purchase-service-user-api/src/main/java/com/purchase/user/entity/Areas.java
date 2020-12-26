package com.purchase.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 行政区域县区信息表
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_areas")
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    private String areaid;

    /**
     * 区域名称
     */
    private String area;

    /**
     * 城市ID
     */
    private String cityid;


}
