package com.purchase.purchasegoods.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_spu")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 货号
     */
    private String sn;

    /**
     * SPU名
     */
    private String name;

    /**
     * 副标题
     */
    private String caption;

    /**
     * 品牌ID
     */
    private Integer brandId;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Brand brand;

    /**
     * 一级分类
     */
    private Integer category1Id;

    /**
     * 二级分类
     */
    private Integer category2Id;

    /**
     * 三级分类
     */
    private Integer category3Id;

    /**
     * 模板ID
     */
    private Integer templateId;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Template template;

    /**
     * 运费模板id
     */
    private Integer freightId;

    /**
     * 图片
     */
    private String image;

    /**
     * 图片列表
     */
    private String images;

    /**
     * 售后服务
     */
    private String saleService;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 规格列表
     */
    private String specItems;

    /**
     * 参数列表
     */
    private String paraItems;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 是否上架
     */
    private String isMarketable;

    /**
     * 是否启用规格
     */
    private String isEnableSpec;

    /**
     * 是否删除
     */
    private String isDelete;

    /**
     * 审核状态
     */
    private String status;


}
