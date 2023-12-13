package com.walsupring.controller.dto.user;

import com.walsupring.user.domain.User;
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
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String loginId;
        private String name;
        private String nickname;

        public Response(User user) {
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.name = user.getName();
            this.nickname = user.getNickname();
        }
    }
}
