package com.example.studyspotbackend.service.user.impl;

import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.models.user.exceptions.PasswordDoNotMatchException;
import com.example.studyspotbackend.models.user.exceptions.UserAlreadyExistsException;
import com.example.studyspotbackend.models.user.helpers.UserRegisterDto;
import com.example.studyspotbackend.service.user.interfaces.UserService;
import com.example.studyspotbackend.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Boolean passwordMatches(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        this.userRepository.deleteById(user.getId());
    }

    @Override
    public Optional<User> register(UserRegisterDto userRegisterDto) {
        User user = this.userRepository.findByEmail(userRegisterDto.getEmail());
        if(user != null){
            throw new UserAlreadyExistsException();
        }
        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            throw new PasswordDoNotMatchException();
        }
        User newUser = new User(
                userRegisterDto.getFirstName(),
                userRegisterDto.getLastName(),
                userRegisterDto.getEmail(),
                passwordEncoder.encode(userRegisterDto.getPassword()));
        this.userRepository.save(newUser);
        return Optional.of(newUser);
    }

    @Override
    public Optional<User> details(String email) {
        return Optional.of(this.userRepository.findByEmail(email));
    }
}