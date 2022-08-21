package com.lyk.store.enums;

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "成功"),

    /* 错误状态码 */
    NOT_FOUND(404, "请求的资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    PARAMETER_EXCEPTION(501, "请求参数校验异常"),

    /* 业务状态码 */
    USER_NOT_EXIST_ERROR(10001, "用户不存在"),
    ;

    private final Integer code;
    private final String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
