package com.example.studyspotbackend.service.user.impl;

import com.example.studyspotbackend.helperfunctinos.HelperFunction;
import com.example.studyspotbackend.models.user.entity.Token;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.models.user.exceptions.PasswordDoNotMatchException;
import com.example.studyspotbackend.models.user.exceptions.UserAlreadyExistsException;
import com.example.studyspotbackend.models.user.helpers.UserRegisterDto;
import com.example.studyspotbackend.service.user.interfaces.TokenService;
import com.example.studyspotbackend.service.user.interfaces.UserService;
import com.example.studyspotbackend.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    private final TokenService tokenService;

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

        Token token = new Token();
        String tokenValue = UUID.randomUUID().toString();
        token.setToken(tokenValue);
        token.setExpirationDate(OffsetDateTime.now().plusMinutes(300));
        token.setUser(newUser);
        tokenService.create(token);

        this.userRepository.save(newUser);
//        HelperFunction.sendRegistrationEmail(userRegisterDto.getEmail(), tokenValue);
        sendMailConfirmation(userRegisterDto.getEmail(), tokenValue);
        return Optional.of(newUser);
    }

    @Override
    public Optional<User> details(String email) {
        return Optional.of(this.userRepository.findByEmail(email));
    }

    @Override
    public User enableAccount(User user) {
        user.setIsEnabled(true);
        return this.userRepository.save(user);
    }


    private void sendMailConfirmation(String email, String token){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email);
            mailMessage.setSubject("Confirmation email!");
//            mailMessage.setFrom("studyspot25@outlook.com");
            mailMessage.setFrom("studyspotproject@outlook.com");
            mailMessage.setText("Click here to confirm your account : "
                    +"http://51.140.255.129:8080/rest/user/confirm-account?token="+token);

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}