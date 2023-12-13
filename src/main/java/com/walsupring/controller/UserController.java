package com.walsupring.controller;

import com.walsupring.controller.dto.user.*;
import com.walsupring.core.response.ApiResult;
import com.walsupring.core.response.Response;
import com.walsupring.user.domain.User;
import com.walsupring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
//통합버전 ->
@RestController  // @Controller + ResponseBody
@RequestMapping("/users")  // prefix처럼 붙음
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)   //status 200이 아니라 201(생성)으로 바꿈
    public ApiResult<UserJoinDto.Response> join(@RequestBody UserJoinDto.Request request) {
        //회원가입
        User user = userService.join(request.getLoginId(), request.getPassword(), request.getName(), request.getNickname());

        return Response.created(new UserJoinDto.Response(user));
    }


    @GetMapping
    public List<GetUsersDto.Response> getUsers() {
        //유저 리스트 반환
        List<User> users = userService.getUsers();
//
//        List<GetUsersDto.Response> responses = new ArrayList<>();
//
//        for (User user: users) {
//            responses.add(new GetUsersDto.Response(user));
//        }
        List<GetUsersDto.Response> responses = users.stream().map(GetUsersDto.Response::new).toList();

        //map  //예제 10개
        //filter
        //flatMap

        return responses;
    }
    //api 요청과 응답에 성공하면
    // code를 s000
    //message : success
    //data : 리턴 데이터
    //data: null

    //유저 없으면
    //code : f001
    //message : 'not found user'
    //data : 리턴데이터 or null

    // 찐에러가 나는 상황 -> 500에러 - server error
    // 내가 의도한 (커스텀 익셉션)에러라면 -> 200코드
    //    //code : f001
    //    //message : 'not found user'
    //    //data : 리턴데이터 or null

    //Exeption을 Handling하기 위한 방법
    //Advice를 활용해서 핸들링



    @GetMapping("/{id}")
    public ApiResult<GetUserDto.Response> getUser(@PathVariable Long id){
        //유저 단건 조회
        //생성자를 통한 방식 -> 선호
        //빌더를 통한 방식 -> nullsafe하지 않음 -> 선호 x
        User user = userService.getUser(id);

        return Response.ok( new GetUserDto.Response(user));
    }

    @PatchMapping("/{id}/change-nickname")
    public ApiResult<UserChangeNicknameDto.Response> changeNickname(@PathVariable Long id, @RequestBody UserChangeNicknameDto.Request request) {
        //유저 닉네임 변경
        User user = userService.changeNickname(id, request.getNickname());

        return Response.ok(new UserChangeNicknameDto.Response(user));

    }

    @PatchMapping("/{id}/change-password")
    public ApiResult<UserChangePasswordDto.Response> changePassword(@PathVariable Long id, @RequestBody UserChangePasswordDto.Request request) {
        //패스워드 변경
        User user = userService.changePassword(id, request.getPassword());
        return Response.ok(new UserChangePasswordDto.Response(user));
    }
}
