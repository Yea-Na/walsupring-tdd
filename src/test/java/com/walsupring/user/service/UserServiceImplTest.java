package com.walsupring.user.service;

import com.walsupring.core.exception.CustomException;
import com.walsupring.user.domain.User;
import com.walsupring.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Transactional // @Test가 붙어 있는 경우
class UserServiceImplTest {

    //의존성 주입
    //1. 필드 주입 <--어플리케이션 코드에서는 절대 사용 안함 -> 남들도 같이 사용
    // 필드 주입 사용 가능한곳 => 테스트코드
    //2. 생성자 주입 -> 생성자가 2개 이상이면  Autowired 생략불가
    //3. 세터 주입(x)


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Test
    void 회원가입_성공() {
        User user = userService.join("loginId", "password", "name", "nickname");
        Assertions.assertThat(user.getId()).isNotNull();
    }

    @Test
    void 내_정보_조회_성공() {
        User user = userRepository.save(new User("loginId", "password", "name", "nickname"));

        User foundUser = userService.getUser(user.getId());

        Assertions.assertThat(foundUser.getId()).isEqualTo(user.getId());
    }

    @Test
    void 내_정보_조회_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(CustomException.class)
                .isThrownBy(() -> userService.getUser(-1L));
    }

    @Test
    void nickname_변경_성공() {
        User user = userRepository.save(new User("loginid","password","name","nickname"));

        user = userService.changeNickname(user.getId(),"nickname1");

        Assertions.assertThat(user.getNickname()).isEqualTo("nickname1");

    }

    @Test
    void 닉네임_변경_실패__존재하지_않는_유저() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> userService.changeNickname(-1L,"nickname1"));
    }

    @Test
    void password_변경_성공() {
        User user = userRepository.save(new User("loginid","password","name","nickname"));

        user = userService.changePassword(user.getId(),"password1");

        Assertions.assertThat(user.getPassword()).isEqualTo("password1");

    }

    @Test
    void 패스워드_변경_실패__존재하지_않는_유저() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> userService.changePassword(-1L,"password1"));
    }

    @Test
    void 유저_리스트_조회_성공() {
        User user1 = userRepository.save(new User("loginid1","password1","name1","nickname1"));
        User user2 = userRepository.save(new User("loginid2","password2","name2","nickname2"));

        List<User> users = userService.getUsers();
//        Assertions.assertThat(users.size()).isEqualTo(2);
        Assertions.assertThat(users).containsExactly(user1,user2);// 자리까지 체크함
    }




    //테스트 코드는 완벽하게 독립적.
    //->

}