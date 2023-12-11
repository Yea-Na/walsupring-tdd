package com.walsupring.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    //성공하는 테스트케이스
    void 유저_생성_성공() { // 한글로 이름 작성가능
        User user = new User("loginId", "1", "이름", "별명");
        //서버와 DB의 시간은 UTC를 기준으로 함
        //한국기준 시간 -> KST -> UTC + 9시간

        //검증 Junit에서 제공되는 것을 활용
        //assertThat(내가 검증하고자 하는 것).isEqualTo(내가 바라는 값)
        assertThat(user.getId()).isNull();
        assertThat(user.getLoginId() ).isEqualTo("loginId");
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("이름");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVATED);
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getUpdatedAt()).isNull();
        assertThat(user.getUpdatedAt()).isNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패_loginId가_Null_혹은_빈값(String loginId) {
//        User user = new User(null, "1", "이름", "별명");
        //이렇게 생성했을때 IllegalArgumentExeption이 잘 터지나?
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User(loginId, "1", "이름", "별명"));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패_password가_Null_혹은_빈값(String password) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User("loginId", password, "이름", "별명"));



    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패_이름이_Null_혹은_빈값(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User("loginId", "password", name, "별명"));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패_별명이_Null_혹은_빈값(String nickname) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User("loginId", "password", "이름", nickname));

    }

    @Test
    void 닉네임_변경_성공() {
        User user = new User("loginId","password","name","nickname");

        user.changeNickname("nickname1");

        Assertions.assertThat(user.getNickname()).isEqualTo("nickname1");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 닉네임_변경_실패__새_닉네임이_null_혹은_빈값(String nickname){
        User user =  new User("loginId", "password","name", "nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> user.changeNickname(nickname));
    }

    @Test
    void 비밀번호_변경_성공() {
        User user = new User("loginId","password","name","nickname");

        user.changePassword("password1");

        Assertions.assertThat(user.getPassword()).isEqualTo("password1");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 비밀번호_변경_실패__새_닉네임이_null_혹은_빈값(String password){
        User user =  new User("loginId", "password","name", "nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> user.changePassword(password));
    }


    //Java Variable Type

    //Java Memory 구조
    //3가지 종류

    //1. static
    //2. stack
    //3. heap



    //1. Primitive Type - stack
    //2. Reference Type - heap

    //1. 기본 자료형 - 초기값이 필요, non-nullabpe = null 인 경우 에러
    //2. 참조 자료형 - 초기값 필요없음, nullable

    //String, int, long, double
    //primitive type
//    int a = 0;
//    long b = 0L;
//    double c = 0;
//    char t = 1;
//
//    //Reference Type
//    String d = null;
//    Integer e = null;
//    Long f = null;
//    Double g = null;



}
