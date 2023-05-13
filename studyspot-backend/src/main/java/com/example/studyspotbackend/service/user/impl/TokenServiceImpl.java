package com.example.studyspotbackend.service.user.impl;

import com.example.studyspotbackend.models.user.entity.Token;
import com.example.studyspotbackend.repository.user.TokenRepository;
import com.example.studyspotbackend.service.user.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Override
    public Token create(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Token findByToken(String token) {
        return this.tokenRepository.findByToken(token);
    }

}
