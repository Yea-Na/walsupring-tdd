package com.walsupring.admin.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class AdminTest {

    @Test
    void 어드민_생성_성공() {
        Admin admin = new Admin("loginId", "password", "name");

        assertThat(admin.getId()).isNull();
        assertThat(admin.getLoginId()).isEqualTo("loginId");
        assertThat(admin.getPassword()).isEqualTo("password");
        assertThat(admin.getName()).isEqualTo("name");
        assertThat(admin.getCreatedAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패__loginId가_null_혹은_빈값(String loginId){
        assertThatIllegalArgumentException().isThrownBy(() -> new Admin(loginId, "password", "name"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패__password가_null_혹은_빈값(String password){
        assertThatIllegalArgumentException().isThrownBy(() -> new Admin("loginId", password, "name"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 어드민_생성_실패__Name이_null_혹은_빈값(String name){
        assertThatIllegalArgumentException().isThrownBy(() -> new Admin("loginId", "password", name));
    }

    @Test
    void 패스워드_변경_성공() {
        Admin admin = new Admin("loginId", "password", "name");

        admin.changePassword("new123");

        Assertions.assertThat(admin.getPassword()).isEqualTo("new123");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 패스워드_변경_실패__패스워드가_null_혹은_빈값(String password) {
        Admin admin = new Admin("loginId", "123", "name");

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> admin.changePassword(password));

    }


}