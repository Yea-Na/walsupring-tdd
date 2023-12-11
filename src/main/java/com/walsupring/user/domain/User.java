package com.walsupring.user.domain;

import com.walsupring.util.Preconditions;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;

import java.time.Instant;

import static com.walsupring.util.Preconditions.require;

@Entity
@Getter  //getter 자동생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//기본생성자 자동생성 ->  그냥 하면 public으로 생성
//@AllArgsConstructor // 모든 파라미터를 가진 생성자. 사용x
public class User {
    //유저 회원가입
    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    //JPA Enum을 다루는 Default -> ORDINAL 0, 1 // 순서로 표시되는데 // 디폴트값으로 사용하지 말것
    @Enumerated(EnumType.STRING) //ORDINAL을 STRING 으로 바꿔줌
    private UserStatus status = UserStatus.ACTIVATED;  //ACTIVATED, INACTIVATED  //과거형.
    private Instant createdAt = Instant.now();
    private Instant updatedAt;



    //DDD 방식을 도입
    //Domain Driven Design(도메인 주도 설계)
    //소프트웨어를 개발한다.
    //개발자 -> 현실세계의 문제를 프로그램으로 해결
    //백엔드 개발자 -> 눈에 보이지않는 서버라는 것을 만드는 것
    //프론트 개발자 -> 눈에 보이는 서버에서 데이터를 받아와서 화면을 만드는 것
    //도메인 -> 비지니스 모델(현실세계의 문제를 해결하기위한 객체(모델))

    //
    // A : 필요없는 물건을 팔려고 하는 니즈
    // B : 물건을 사고싶은데 싸게 사고 싶음

    //유저가 주도해서 설계?
    // 객체지향 프로그래밍 -> 객체? -> 수동적이지 않고 능동적임 -> 객체지향의 사실과 오해(책)
    //능동적? -> 서로 협력관계에 있어야 한다
    //User라는 도메인이 상태를 가져야 함 -> 도메인 자신이 상태를 관리해야함
    //User라는도메인이 어떤 행동을 할 수 있어야 함 -> 내 행동을 남에게 맡기지 않는다
    //도메인이 능동적일 수 있게 설계하는 것 -> DDD의 원초적인 개념


    //일단 삭제(테스트코드 먼저)
//    public User(String loginId, String password, String name, String nickname) {
//        //방어로직-> 생성되는 데 방어 = 능동적으로 생성을 관리
//        //도메인이 안정적으로 생성이 된다.
//        this.loginId = loginId;
//        this.password = password;
//        this.name = name;
//        this.nickname = nickname;
//    }


    public User(String loginId, String password, String name, String nickname) {
        require(Strings.isNotBlank(loginId),"loginId must not be null");
        require(Strings.isNotBlank(password),"password must not be null");
        require(Strings.isNotBlank(name),"name must not be null");
        require(Strings.isNotBlank(nickname),"nickname must not be null");

//        if(Strings.isBlank(password)) {
//            throw new IllegalArgumentException("password must not be null");
//        }

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }


    public void changeNickname(String nickname) {
        require(Strings.isNotBlank(nickname),"nickname must not be null");
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        require(Strings.isNotBlank(password),"password must not be null");
        this.password = password;
    }
}
