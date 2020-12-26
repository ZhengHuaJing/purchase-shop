package com.purchase.file.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上传者
     */
    @ApiModelProperty(value = "上传者", name = "author")
    @NotBlank(message = "上传者不能为空")
    private String author;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", name = "name")
    @NotBlank(message = "文件名不能为空")
    private String name;

    /**
     * 地址
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件内容
     */
    @ApiModelProperty(value = "文件内容", name = "content")
    @NotBlank(message = "文件内容不能为空")
    @TableField(exist = false)
    private byte[] content;

    /**
     * 拓展名
     */
    @ApiModelProperty(value = "文件拓展名", name = "ext")
    @NotBlank(message = "文件名拓展名不能为空")
    private String ext;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
