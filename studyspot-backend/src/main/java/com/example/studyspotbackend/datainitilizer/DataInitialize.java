package com.example.studyspotbackend.datainitilizer;

import com.example.studyspotbackend.models.user.entity.Role;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitialize {
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        if(userRepository.findByEmail("admin.dev@studyspot.com") == null){
            User user = new User("Admin", "Admin", "admin.dev@studyspot.com", "Admin123#");
            user.setRole(Role.ROLE_ADMIN);
            user.setIsEnabled(true);
            this.userRepository.save(user);
        }
    }
}
