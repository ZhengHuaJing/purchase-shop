package com.purchase.purchasecommon.exception;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/12/12 14:34
 * @Description: 错误码定义规则为5为数字，前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常。
 * 10: 通用
 * 11: 健康预约
 * 12: 健康评估
 * 13: 知识库
 * 14: 健康干预
 * 15: 用户
 * 16: 文件
 * 17: 日志
 */
public enum CodeEnum {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败"),
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10003, "验证码获取频率太高，请稍后再试"),

    USER_EXIST_EXCEPTION(15001, "存在相同的用户"),
    PHONE_EXIST_EXCEPTION(15002, "存在相同的手机号"),
    LOGINACCT_PASSWORD_EXCEPTION(15003, "账号或密码错误"),
    ;

    private Integer code;

    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
