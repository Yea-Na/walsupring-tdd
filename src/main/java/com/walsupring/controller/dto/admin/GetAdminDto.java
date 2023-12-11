package com.walsupring.controller.dto.admin;

import com.walsupring.admin.domain.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

public class GetAdminDto {
    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String loginId;
        private String name;
        private Instant createdAt;

        public Response(Admin admin) {
            this.id = admin.getId();
            this.loginId = admin.getLoginId();
            this.name = admin.getName();
            this.createdAt = admin.getCreatedAt();
        }
    }
}
