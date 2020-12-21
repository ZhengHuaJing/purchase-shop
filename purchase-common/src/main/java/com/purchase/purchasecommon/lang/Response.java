package com.purchase.purchasecommon.lang;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhenghuajing
 */
@Setter
@Getter
public class Response implements Serializable {

    @ApiModelProperty(value = "状态码", name = "code")
    private Integer code;

    @ApiModelProperty(value = "消息", name = "msg")
    private String msg;

    @ApiModelProperty(value = "数据", name = "data")
    private Object data;

    public Response() {
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static Response response(Integer code, String msg, Object data) {
        return new Response(code, msg, data);
    }

    public static Response success() {
        return response(200, "操作成功", null);
    }

    public static Response success(Object data) {
        return response(200, "操作成功", data);
    }

    public static Response success(String msg, Object data) {
        return response(200, msg, data);
    }

    public static Response fail(Integer code, String msg) {
        return response(code, msg, null);
    }

    public static Response fail(String msg) {
        return response(400, msg, null);
    }

    public static Response fail() {
        return response(400, "操作失败", null);
    }

    public static Response fail(Integer code, String msg, Object data) {
        return response(code, msg, data);
    }

    public static Response assess(Boolean isSuccess) {
        if (isSuccess) {
            return Response.success();
        } else {
            return Response.fail();
        }
    }
}
