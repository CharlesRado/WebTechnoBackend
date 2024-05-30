package com.project.delicioso.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    DELEVERYMAN_READ("deleveryman:read"),
    DELEVERYMAN_UPDATE("deleveryman:update"),
    DELEVERYMAN_CREATE("deleveryman:create"),
    DELEVERYMAN_DELETE("deleveryman:delete");

    @Getter
    private final String permission;
}
