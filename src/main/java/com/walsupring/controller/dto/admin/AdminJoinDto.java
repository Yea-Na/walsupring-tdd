package com.walsupring.controller.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminJoinDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String loginId;
        private String password;
        private String name;
    }

    public static class Response {

    }
}
