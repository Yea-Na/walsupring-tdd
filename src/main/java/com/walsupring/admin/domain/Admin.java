package com.walsupring.admin.domain;

import com.walsupring.util.Preconditions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private Instant createdAt = Instant.now();



    public Admin(String loginId, String password, String name) {
        Preconditions.require(Strings.isNotBlank(loginId), "loginId must not be null");
        Preconditions.require(Strings.isNotBlank(password), "password must not be null");
        Preconditions.require(Strings.isNotBlank(name), "name must not be null");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }


    public void changePassword(String password) {
        Preconditions.require(Strings.isNotBlank(password),"password must not be null");
        this.password = password;
    }
}
