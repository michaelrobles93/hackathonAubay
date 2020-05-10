package com.aubay.hackathon.enums;

import java.util.Arrays;

public enum RoleEnum {

    MANAGER(15),
    GESTOR(13),
    EXTERNO(12);

    private long code;

    RoleEnum(long code) {
        this.code = code;
    }

    public static RoleEnum getByCode(long code) {

        return Arrays.stream(RoleEnum.values()).filter(role -> role.code == code).findFirst().get();
    }

    public long getCode() {
        return this.code;
    }
}
