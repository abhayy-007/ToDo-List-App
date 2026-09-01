package com.example.enums;

import java.util.Set;

import lombok.Getter;

@Getter
public enum Roles {

    USER(Set.of(Permissions.USER_DELETE, Permissions.USER_READ, Permissions.USER_WRITE, Permissions.USER_UPDATE));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

}
