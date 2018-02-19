package com.gmail.v.varvaruk89.model;

public enum UserRole {
    ADMIN,MENEGER;

    @Override
    public String toString() {
        return "ROLE_"+name();
    }
}
