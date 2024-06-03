package com.project.delicioso.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    DELIVERYMAN_READ("deliveryman:read"),
    DELIVERYMAN_UPDATE("deliveryman:update"),
    DELIVERYMAN_CREATE("deliveryman:create"),
    DELIVERYMAN_DELETE("deliveryman:delete");

    @Getter
    private final String permission;
}
