package com.example.studyspotbackend.models.user.helpers;

import lombok.Getter;

@Getter
public class UserRegisterDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
}