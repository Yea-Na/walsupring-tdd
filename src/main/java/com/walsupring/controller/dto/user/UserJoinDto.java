package com.walsupring.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserJoinDto {
    //이너 클래스
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String loginId;
        private String password;
        private String name;
        private String nickname;
    }

    public static class Response {

    }
}
