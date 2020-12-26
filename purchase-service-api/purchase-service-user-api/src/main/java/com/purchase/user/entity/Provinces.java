package com.purchase.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 省份信息表
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_provinces")
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省份ID
     */
    private String provinceid;

    /**
     * 省份名称
     */
    private String province;


}
