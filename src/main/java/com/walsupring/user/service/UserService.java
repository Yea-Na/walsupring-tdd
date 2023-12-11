package com.walsupring.user.service;

import com.walsupring.user.domain.User;

import java.util.List;

public interface UserService {
    User join(String loginId, String password, String name, String nickname);

    User getUser(Long id);

    User changeNickname(Long id, String nickname);

    User changePassword(Long id, String password);

    List<User> getUsers();

    //join, create, save, persist

}
