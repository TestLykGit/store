package com.lyk.store.exception;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppExceptionCode {
    SUCCESS(200, "Success", "请求成功"),
    BAD_REQUEST(400, "Bad Request", "参数错误"),
    BAD_REQUEST_PARAMS_VERIFY_ERROR(4001, "Bad Request Params Verify Error", "参数校验错误"),
    BAD_REQUEST_PARAMS_MATCHING_ERROR(4002, "Bad Request Params Matching Error", "参数匹配错误"),
    UNAUTHORIZED(401, "Unauthorized", "登录过期"),
    PAYMENT_REQUIRED(402, "Payment Required", "请付费后再访问"),
    FORBIDDEN(403, "Forbidden", "权限不足"),
    NOT_FOUND(404, "Not Found", "找不到资源"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "禁止调用"),
    CONFLICT(409, "Conflict", "资源冲突"),
    GONE(410, "Gone", "资源不存在"),
    TOO_MANY_REQUESTS(429, "Too Many Requests", "调用频率过高"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "服务器异常"),
    INTERNAL_SERVER_CALL_ERROR(5001, "Internal Server Call Error", "服务器异常"),
    NOT_IMPLEMENTED(501, "Not Implemented", "未实现的操作"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable", "服务器正在维护");

    private final int code;
    private final String name;
    private final String message;

    AppExceptionCode(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    @JsonCreator
    public static AppExceptionCode valueOf(int code) {
        AppExceptionCode[] var1 = values();
        int var2 = var1.length;

        for (AppExceptionCode element : var1) {
            if (element.code == convertToCompatibleVersionCode(code)) {
                return element;
            }
        }

        return null;
    }

    private static int convertToCompatibleVersionCode(int code) {
        switch (code) {
            case 400:
                return BAD_REQUEST.getCode();
            case 500:
                return INTERNAL_SERVER_ERROR.getCode();
            default:
                return code;
        }
    }

    @JsonValue
    public final int getCode() {
        return this.code;
    }

    public final String getName() {
        return this.name;
    }

    public final String getMessage() {
        return this.message;
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}

