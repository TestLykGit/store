package com.lyk.store.exception;

import cn.hutool.core.util.StrUtil;
import com.lyk.store.config.DataResult;

public class AppException extends RuntimeException {

    private AppExceptionCode errorCode;

    public AppException(AppExceptionCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AppException(Throwable cause, AppExceptionCode errorCode) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public AppException(AppExceptionCode errorCode, String message, Object... messageParams) {
        super(StrUtil.format(message, messageParams));
        this.errorCode = errorCode;
    }

    public AppException(Throwable cause, AppExceptionCode errorCode, String message, Object... messageParams) {
        super(StrUtil.format(message, messageParams), cause);
        this.errorCode = errorCode;
    }

    public AppExceptionCode getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(AppExceptionCode errorCode) {
        this.errorCode = errorCode;
    }

    public String toJsonString() {
        return DataResult.fail(this).toJsonString();
    }
}
