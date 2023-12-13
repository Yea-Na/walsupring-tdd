package com.walsupring.controller;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walsupring.controller.dto.user.UserChangeNicknameDto;
import com.walsupring.controller.dto.user.UserChangePasswordDto;
import com.walsupring.controller.dto.user.UserJoinDto;
import com.walsupring.user.domain.User;
import com.walsupring.user.domain.UserRepository;
import com.walsupring.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@AutoConfigureRestDocs
class UserControllerTest {
    //API문서화는 반드시 함
    //혼자 API랑 화면까지 만지는 경우는 안만듦

    //방법이 4가지정도 존재함
    //1. Swagger(스웨거)
    // - 장점
    //      .이쁨
    //      .손쉽게 만들 수 있음
    //      .어노테이션 기반으로 만들어진다
    //      .실무에서 엄청나게 많이 쓴다.
    //      .실제 문서에서 api를 테스트 해볼 수 있다.

    // - 단점
    //      .어노테이션이 너무 더럽다.
    //      .어플리케이션 코드를 침범한다.
    //      .테스트코드 없이 api문서를 만들 수 있다.
    //      .문서의 데이터를 임의로 적는다. 실제 api와는 무관.

    //2. restDocs(레스트독스)
    // - 장점
    //      .테스트코드가 없으면 문서화가 안된다.(테스트코드강제)
    //      .실제 발생한 요청과 응답을 토대로 문서화가 된다.(신뢰성이 있다)
    //- 단점
    //      .못생겼다..
    //      .문서에서 테스트 할 수 없음
    //      .테스트코드를 사용하지 않는 회사라면 도입이 힘들 수 있음

    //3. epages(restDocs + openapi + SwaggerUi 합쳐둠) <--오픈소스
    //  단점이 별로 존재하지 않는 현시대 최고의 api 문서화 바업
    //  단점) 코드가 너무 길어진다..
    //


    //4. 노션 등에 수작업


    @Autowired
    private MockMvc mockMvc;  // 실제 요청 날릴 객체

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;



    @Test
    void 회원가입_성공() throws Exception {
        UserJoinDto.Request request = new UserJoinDto.Request("loginId","123","이름","별명");

        mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").isNotEmpty(),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("이름"),
                        jsonPath("$.data.nickname").value("별명")
//                        jsonPath("$.data.status").value(UserStatus.ACTIVATED.name())
//                        jsonPath("$.data.createdAt").isNotEmpty(),
//                        jsonPath("$.data.updatedAt").isEmpty()
                )
                .andDo(document("user/create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        ResourceDocumentation.resource(
                                ResourceSnippetParameters.builder()
                                        .tag("USER")
                                        .description("회원가입 api")
                                        .requestFields(
                                                List.of(
                                                        PayloadDocumentation.fieldWithPath("loginId").type(JsonFieldType.STRING).description("loginId"),
                                                        PayloadDocumentation.fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                                        PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                                        PayloadDocumentation.fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname")
                                                )
                                        )
                                        .responseFields(
                                                List.of(
                                                        PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.STRING).description("S000"),
                                                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("success"),
                                                        PayloadDocumentation.fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                                        PayloadDocumentation.fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("loginId"),
                                                        PayloadDocumentation.fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                                        PayloadDocumentation.fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("별명")

                                                        )
                                        )
                                        .build()
                        )));

        Optional<User> optionalUser = userRepository.findByLoginId("loginId");

        Assertions.assertThat(optionalUser.isPresent()).isTrue();
    }


    @Test
    void 유저_리스트_조회_성공() throws Exception {
        User user1 = userRepository.save(new User("loginId1", "password1", "name1","nickname1"));
        User user2 = userRepository.save(new User("loginId2", "password2", "name2","nickname2"));

        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/users"));

        resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andExpectAll(유저_리스트_검증(0,user1));
        resultActions.andExpectAll(유저_리스트_검증(1,user2));
//        resultActions.andDo(DocsUtil.createDocs(
//                        "USER","user/get-list",
//                        "유저 리스트 조회 API",
//                        resultActions
//        ));
    }



    @Test
    void 유저_단건_조회_성공() throws Exception {
        User user = userRepository.save(new User("loginId", "123", "name", "nickname"));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}", user.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        jsonPath("$.code").value("S000"),
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").value(user.getId()),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("name"),
                        jsonPath("$.data.nickname").value("nickname"),
                        jsonPath("$.data.status").value(UserStatus.ACTIVATED.name()),
                        jsonPath("$.data.createdAt").isNotEmpty(),
                        jsonPath("$.data.updatedAt").isEmpty()
                )
                .andDo(document("user/get-one",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        ResourceDocumentation.resource(
                                ResourceSnippetParameters.builder()
                                        .tag("USER")
                                        .description("유저 단건 조회 API")
                                        .pathParameters(new ParameterDescriptorWithType("id").description(1).type(SimpleType.NUMBER))
                                        .responseFields(
                                                List.of(
                                                        PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.STRING).description("S000"),
                                                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("success"),
                                                        PayloadDocumentation.fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("id"),
                                                        PayloadDocumentation.fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("loginId"),
                                                        PayloadDocumentation.fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                                        PayloadDocumentation.fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("별명"),
                                                        PayloadDocumentation.fieldWithPath("data.status").type(JsonFieldType.STRING).description("상태"),
                                                        PayloadDocumentation.fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("가입일시"),
                                                        PayloadDocumentation.fieldWithPath("data.updatedAt").type(JsonFieldType.NULL).description("수정일시")
                                                )
                                        )
                                        .build()
                        )
                ));
    }

    @Test
    void 유저_단건_조회_실패__존재_하지_않는_id() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}", -1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        jsonPath("$.code").value("F001"),
                        jsonPath("$.message").value("not found user")
                )
                .andDo(document("user/not-found",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        ResourceDocumentation.resource(
                                ResourceSnippetParameters.builder()
                                        .tag("USER")
                                        .description("유저 단건 조회 API")
                                        .pathParameters(new ParameterDescriptorWithType("id").description(1).type(SimpleType.NUMBER))
                                        .responseFields(
                                                List.of(
                                                        PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.STRING).description("F001"),
                                                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("not found user"),
                                                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.NULL).description("null")
                                                )
                                        )
                                        .build()
                        )
                ));
    }
    @Test
    void 닉네임_변경_성공() throws Exception {
        UserChangeNicknameDto.Request request = new UserChangeNicknameDto.Request("newNickname");
        User user = userRepository.save(new User("loginId", "password", "name","nickname"));
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}/change-nickname",user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(jsonPath("$.code").value("S000"),
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").value(user.getId()),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("name"),
                        jsonPath("$.data.nickname").value("newNickname"),
                        jsonPath("$.data.status").value(UserStatus.ACTIVATED.name()),
                        jsonPath("$.data.createdAt").isNotEmpty(),
                        jsonPath("$.data.updatedAt").isEmpty())
                .andDo(document("user/nickName-change",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));


    }

    @Test
    void 패스워드_변경_성공() throws Exception {
        UserChangePasswordDto.Request request = new UserChangePasswordDto.Request("newPassword");
        User user = userRepository.save(new User("loginId","password","name","nickname"));
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}/change-password",user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(jsonPath("$.code").value("S000"),
                        jsonPath("$.message").value("success"),
                        jsonPath("$.data.id").value(user.getId()),
                        jsonPath("$.data.loginId").value("loginId"),
                        jsonPath("$.data.name").value("name"),
                        jsonPath("$.data.nickname").value("nickname"),
                        jsonPath("$.data.status").value(UserStatus.ACTIVATED.name()),
                        jsonPath("$.data.createdAt").isNotEmpty(),
                        jsonPath("$.data.updatedAt").isEmpty())
                .andDo(document("user/password-change",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
        Assertions.assertThat(user.getPassword()).isEqualTo("newPassword");
    }

    private ResultMatcher[] 유저_리스트_검증(int index, User user) {  //
        return List.of(
                jsonPath("$.[" + index + "].id").value(user.getId()),
                jsonPath("$.[" + index + "].loginId").value(user.getLoginId()),
                jsonPath("$.[" + index + "].name").value(user.getName()),
                jsonPath("$.[" + index + "].nickname").value(user.getNickname()),
                jsonPath("$.[" + index + "].status").value(user.getStatus().name()),
                jsonPath("$.[" + index + "].createdAt").isNotEmpty(),
                jsonPath("$.[" + index + "].updatedAt").isEmpty())
                .toArray(ResultMatcher[]::new);

    }






}

