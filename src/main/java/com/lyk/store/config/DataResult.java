package com.lyk.store.config;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lyk.store.exception.AppException;
import com.lyk.store.exception.AppExceptionCode;

import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class DataResult<T> {

    private static final AppExceptionCode SUCCESS_CODE;
    private static final AppExceptionCode FAIL_CODE;
    private final AppExceptionCode status;
    private String massage;
    private T data;

    private DataResult(AppException appException) {
        this.massage = appException.getMessage();
        this.status = appException.getErrorCode();
    }

    public DataResult(AppExceptionCode status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    @JsonCreator
    public DataResult(AppExceptionCode status, String massage, T data) {
        this.status = status;
        this.massage = massage;
        this.data = data;
    }

    public AppExceptionCode getStatus() {
        return this.status;
    }

    public DataResult<T> setMessage(String message) {
        this.massage = message;
        return this;
    }

    public String getMessage() {
        return this.massage;
    }

    public DataResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return this.data;
    }

    @JsonIgnore
    public T getDataThrow() {
        if (this.status != AppExceptionCode.SUCCESS) {
            throw new AppException(this.status, this.massage);
        } else {
            return this.data;
        }
    }

    public void checkStatus() {
        if (this.status != AppExceptionCode.SUCCESS) {
            throw new AppException(this.status, this.massage);
        }
    }

    public static <T> DataResult success() {
        return new DataResult(SUCCESS_CODE, SUCCESS_CODE.getMessage());
    }

    public static <T> DataResult<T> success(T data) {
        return new DataResult(SUCCESS_CODE, SUCCESS_CODE.getMessage(), data);
    }

    public static <T> DataResult<T> fail(AppException appException) {
        return new DataResult(appException);
    }

    public static <T> DataResult<T> fail(String message) {
        return new DataResult(FAIL_CODE, message);
    }

    public static <T> DataResult<T> fail(AppExceptionCode status, String message) {
        return new DataResult(status, message);
    }

    public boolean isSuccess() {
        return Objects.equals(this.status, SUCCESS_CODE);
    }

    public boolean isFail() {
        return !Objects.equals(this.status, SUCCESS_CODE);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    static {
        SUCCESS_CODE = AppExceptionCode.SUCCESS;
        FAIL_CODE = AppExceptionCode.INTERNAL_SERVER_ERROR;
    }
}

