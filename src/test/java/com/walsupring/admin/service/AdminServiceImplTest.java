package com.walsupring.admin.service;

import com.walsupring.admin.domain.Admin;
import com.walsupring.admin.domain.AdminRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    void 어드민_회원가입_성공() {
        Admin admin = adminService.join("loginId","password","name");

        Assertions.assertThat(admin.getId()).isNotNull();
    }

    @Test
    void 어드민_정보_조회_성공() {
        Admin admin = adminRepository.save(new Admin("loginId","password","name"));

        Admin foundAdmin = adminService.getAdmin(admin.getId());

        Assertions.assertThat(foundAdmin.getId()).isEqualTo(admin.getId());
    }

    @Test
    void 어드민_정보_조회_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> adminService.getAdmin(-1L));

    }

    @Test
    void 어드민_비밀번호_변경_성공() {
        Admin admin = adminRepository.save(new Admin("loginId", "password","name"));

        admin = adminService.changePassword(admin.getId(),"new123");

        Assertions.assertThat(admin.getPassword()).isEqualTo("new123");
    }

    @Test
    void 어드민_비밀번호_변경_실패__존재_하지_않는_어드민() {
        Admin admin = adminRepository.save(new Admin("loginId", "password","name"));

        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> adminService.changePassword(-1L,"new123"));
    }






}