package com.walsupring.controller.dto.user;

import com.walsupring.user.domain.User;
import com.walsupring.user.domain.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

public class GetUsersDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String loginId;
        private String name;
        private String nickname;
        private UserStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        public Response(User user) {
            this.id = user.getId();
            this.loginId = user.getLoginId();
            this.name = user.getName();
            this.nickname = user.getNickname();
            this.status = user.getStatus();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }
}
