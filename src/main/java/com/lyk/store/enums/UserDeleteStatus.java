package com.lyk.store.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserDeleteStatus {
    DELETE(0, false),
    ISDELETE(1, true),
    ;
    private final long code;
    private final boolean status;

    public static UserDeleteStatus valueOfCode(int code) {
        for (UserDeleteStatus userDeleteStatus : UserDeleteStatus.values()) {
            if (userDeleteStatus.getCode() == code) {
                return userDeleteStatus;
            }
        }
        throw new IllegalArgumentException("UserStatus.valueOf code not found,code=" + code);
    }
}
