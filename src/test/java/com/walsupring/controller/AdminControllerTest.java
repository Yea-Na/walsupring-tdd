package com.walsupring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walsupring.admin.domain.Admin;
import com.walsupring.admin.domain.AdminRepository;
import com.walsupring.controller.dto.admin.AdminChangePasswordDto;
import com.walsupring.controller.dto.admin.AdminJoinDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void 어드민_생성_성공() throws Exception {
        AdminJoinDto.Request request = new AdminJoinDto.Request("loginId","123","이름");
        mockMvc.perform(MockMvcRequestBuilders.post("/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print());

        Optional<Admin> optionalAdmin = adminRepository.findByLoginId("loginId");
        Assertions.assertThat(optionalAdmin.isPresent()).isTrue();
    }

    @Test
    void 어드민_단건_조회_성공() throws Exception {
        Admin admin = adminRepository.save(new Admin("loginId", "password", "name"));
        mockMvc.perform(MockMvcRequestBuilders.get("/admins/{id}",admin.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(jsonPath("$.code").value("S000"),
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").value(1),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("name"),
                        jsonPath("$.data.createdAt").isNotEmpty()
                );
    }

    @Test
    void 패스워드_변경_성공() throws Exception {
        AdminChangePasswordDto.Request request = new AdminChangePasswordDto.Request("newPassword");
        Admin admin = adminRepository.save(new Admin("loginId","password","name"));
        mockMvc.perform(MockMvcRequestBuilders.patch("/admins/{id}/change-password",admin.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(jsonPath("$.code").value("S000"),
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").value(1),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("name"),
                        jsonPath("$.data.createdAt").isNotEmpty()
                );
    }
}