package com.gmail.v.varvaruk89.entities.tsm;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;

    }


}
