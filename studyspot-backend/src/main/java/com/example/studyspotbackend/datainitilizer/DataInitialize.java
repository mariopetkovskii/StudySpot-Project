package com.example.studyspotbackend.datainitilizer;

import com.example.studyspotbackend.models.user.entity.Role;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitialize {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        if(userRepository.findByEmail("admin.dev@studyspot.com") == null){
            User user = new User("Admin", "Admin", "admin.dev@studyspot.com", passwordEncoder.encode("Admin123#"));
            user.setRole(Role.ROLE_ADMIN);
            user.setIsEnabled(true);
            this.userRepository.save(user);
        }
    }
}
