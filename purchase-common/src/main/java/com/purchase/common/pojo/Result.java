package com.purchase.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhenghuajing
 */
@Setter
@Getter
public class Result implements Serializable {

    @ApiModelProperty(value = "状态码", name = "code")
    private Integer code;

    @ApiModelProperty(value = "消息", name = "msg")
    private String msg;

    @ApiModelProperty(value = "数据", name = "data")
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static Result result(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success() {
        return result(200, "操作成功", null);
    }

    public static Result success(Object data) {
        return result(200, "操作成功", data);
    }

    public static Result success(String msg, Object data) {
        return result(200, msg, data);
    }

    public static Result fail(Integer code, String msg) {
        return result(code, msg, null);
    }

    public static Result fail(String msg) {
        return result(400, msg, null);
    }

    public static Result fail() {
        return result(400, "操作失败", null);
    }

    public static Result fail(Integer code, String msg, Object data) {
        return result(code, msg, data);
    }

    public static Result assess(Boolean isSuccess) {
        if (isSuccess) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }
}
