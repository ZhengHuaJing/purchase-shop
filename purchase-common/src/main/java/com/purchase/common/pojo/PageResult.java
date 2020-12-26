package com.purchase.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/12/19 21:06
 * @Description: 分页结果类
 */
@Getter
@Setter
public class PageResult<T> {

    @ApiModelProperty(value = "总记录数", name = "total")
    private Integer total;

    @ApiModelProperty(value = "记录", name = "rows")
    private List<T> rows;

    public PageResult(List<T> rows, int total) {
        this.total = total;
        this.rows = rows;
    }
}
