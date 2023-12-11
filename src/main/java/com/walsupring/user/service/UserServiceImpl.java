package com.walsupring.user.service;

import com.walsupring.core.exception.CustomException;
import com.walsupring.core.exception.ErrorCode;
import com.walsupring.user.domain.User;
import com.walsupring.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User join(String loginId, String password, String name, String nickname) {
        return userRepository.save(new User(loginId, password, name, nickname));
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return user;
    }

    @Override
    public User changeNickname(Long id, String nickname) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        user.changeNickname(nickname);
        return user;
    }

    @Override
    public User changePassword(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        user.changePassword(password);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
