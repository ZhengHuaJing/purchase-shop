package com.purchase.purchasecommon.lang;

import lombok.Data;

import java.util.List;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/12/19 21:06
 * @Description: 分页结果类
 */
@Data
public class PageResult<T> {

    private Long total;//总记录数
    private List<T> rows;//记录
}
