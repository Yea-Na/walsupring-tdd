package com.walsupring.admin.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void 로그인_아이디로_어드민_검색() {
        Admin admin = adminRepository.save(new Admin("loginId", "123", "이름"));
        Admin foundAdmin = adminRepository.findByLoginId(admin.getLoginId()).orElseThrow();

        org.assertj.core.api.Assertions.assertThat(foundAdmin).isSameAs(admin);
    }

    @Test
    void 로그인_아이디로_어드민_검색_실패__검색_결과_없음() {
        Admin admin = adminRepository.save(new Admin("loginId", "123", "이름"));

        org.assertj.core.api.Assertions.assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> adminRepository.findByLoginId("b").orElseThrow());

    }
}