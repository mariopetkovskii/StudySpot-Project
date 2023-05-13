package com.example.studyspotbackend.service.user.interfaces;

import com.example.studyspotbackend.models.user.entity.Token;

public interface TokenService {
    Token create (Token token);

    Token findByToken (String token);
}
