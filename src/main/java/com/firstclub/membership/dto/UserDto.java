package com.firstclub.membership.dto;

import lombok.Data;

public class UserDto {

    @Data
    public static class RegisterRequest {
        private String name;
        private String email;
    }

    @Data
    public static class LoginRequest {
        private String email;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private String email;
    }

}
