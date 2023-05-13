package com.example.studyspotbackend.service.user.interfaces;

import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.models.user.helpers.UserRegisterDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByEmail(String email);

    User save(User user);

    Boolean passwordMatches(User user, String password);

    List<User> findAll();

    void deleteUserByEmail(String email);

    Optional<User> register(UserRegisterDto userRegisterDto);

    Optional<User> details(String email);

    User enableAccount(User user);

}