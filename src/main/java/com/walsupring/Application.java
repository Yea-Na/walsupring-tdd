package com.walsupring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    //Junit이라는 테스트 프레임워크가 스프링에서 제공됨
    //내가 만든 코드를 실행하고 검증할 수 있음
    // main -> application code
    // test -> test code

    //tdd -> test Driven Development  = 테스트 주도 개발
    //매우 단순한데 적용하기는 쉽지 않음
    //장단점이 분명
    //장점 :
    // 1. 코드를 변경하는 데 두려움이 없어짐
    // 2. 오류에 대한 대처가 쉬워짐
    // -> 어떻게 하면 오류가 발생할까?
    // 3. 좋은 설계에 가까워진다
    //단점:
    // 1. 시간이 많이 든다. = 비용이 많이 든다
    // 2. 테스트코드에 익숙하지 않다면 고통스럽다

    //테스트 코드를 작성한다는 것 = 오류가 100% 사라지는 것이 아님
    //오류가 70%정도 감소
    //30%에 대한 오류 -> 30%에대한 생각을 못했구나
    //


    //일반적인 수순.
    // 개발완료 -> 유지보수 -> 유지보수가 쉽지않음
    // -> 버려짐 -> 근데 사용자는 계속 있음
    // -> 결심함 -> 고도화 -> 그냥 새로 만듦

    //서비스 회사들에서 TDD방식 채택

    //TDD 그래서 어떻게?
    //1. 테스트코드부터 작성 -> 실행 -> 실패
    //2. 성공할 수 있도록 어플리케이션 코드를 작성
    //3. 테스트코드 -> 실행 -> 성공
    //4. 다시 실패하는 테스트코드를 덧붙임 -> 실행 ->실패
    //5. 다시 성공할 수 있도록 어플리케이션 코드를 덧붙임
    //6. 다시 테스트코드 실행 -> 성공
    //반복
    //리팩터링 진행   -> 중복을 제거하고 더러운 코드를 깔끔하게 바꿔주고 등

    //어플리케이션 코드를 먼저 작성하고 테스트 코드를 작성
    //이 경우 테스트코드를 추가한 것. TDD 방법론으로 개발한 건 아님



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
