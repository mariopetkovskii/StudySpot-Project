package com.example.studyspotbackend.repository.user;

import com.example.studyspotbackend.models.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    @Modifying
    @Transactional
    void deleteTokenByToken(String token);
}