package com.walsupring.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest // 데이터 관련한 부분만 ioc컨테이너에 올림
@AutoConfigureTestDatabase(replace = NONE)
//@SpringBootTest //모든 빈을 ioc컨테이너에 올림
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void 로그인_아이디로_유저_검색() {
        User user = userRepository.save(new User("loginId", "123", "이름", "별명"));
        User foundUser = userRepository.findByLoginId(user.getLoginId()).orElseThrow();

        Assertions.assertThat(foundUser).isSameAs(user);

    }

    @Test
    void 로그인_아이디로_유저_검색_실패__검색_결과_없음() {
        User user = userRepository.save(new User("loginId", "123", "이름", "별명"));
//        User foundUser = userRepository.findByLoginId("a").orElseThrow();

        Assertions.assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userRepository.findByLoginId("a").orElseThrow());

    }

}