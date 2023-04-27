package com.example.studyspotbackend.models.user.helpers;

import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
