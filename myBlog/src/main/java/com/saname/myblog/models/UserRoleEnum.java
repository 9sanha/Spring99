package com.saname.myblog.models;

public enum UserRoleEnum {
    USER(Authority.USER), // 사용자 권한
    ADMIN(Authority.ADMIN); // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority){
        this.authority = authority;
    }

    private static final String PREFIX_ROLE = "ROLE_";

    public String getAuthority(){
        return PREFIX_ROLE+USER;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
