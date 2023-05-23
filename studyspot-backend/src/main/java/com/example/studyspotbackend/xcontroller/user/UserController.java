package com.example.studyspotbackend.xcontroller.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.studyspotbackend.QuizResultsHelperWithCourse;
import com.example.studyspotbackend.models.user.entity.Token;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.models.user.helpers.UserInfoDto;
import com.example.studyspotbackend.models.user.helpers.UserRegisterDto;
import com.example.studyspotbackend.service.quiz.interfaces.QuizResultsService;
import com.example.studyspotbackend.service.user.interfaces.TokenService;
import com.example.studyspotbackend.service.user.interfaces.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final QuizResultsService quizResultsService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto userRegisterDto) {
        return this.userService.register(userRegisterDto)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> listAll() {
        return this.userService.findAll();
    }

    @PostMapping("/details")
    public ResponseEntity<UserInfoDto> userDetails(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            DecodedJWT jwt = JWT.decode(token);
            String email = jwt.getSubject();
            return this.userService.details(email)
                    .map(user -> {
                        UserInfoDto userInfoDto = new UserInfoDto();
                        userInfoDto.setEmail(user.getEmail());
                        userInfoDto.setId(user.getId());
                        userInfoDto.setFirstName(user.getFirstName());
                        userInfoDto.setLastName(user.getLastName());
                        return ResponseEntity.ok().body(userInfoDto);
                    })
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/get/quiz/results")
    public List<QuizResultsHelperWithCourse> getAllQuizResults(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        DecodedJWT jwt = JWT.decode(token);
        String email = jwt.getSubject();
        User user = this.userService.findByEmail(email);
        return this.quizResultsService.getUserCoursePoints(user);
    }

    @GetMapping("/confirm-account")
    public void confirmAccount(@RequestParam String token, HttpServletResponse response) throws IOException {
        Token tokenInDB = this.tokenService.findByToken(token);
        if (tokenInDB != null) {
            if (tokenInDB.getExpirationDate().isBefore(OffsetDateTime.now())) {
                this.userService.deleteUserByEmail(tokenInDB.getUser().getEmail());
                response.sendRedirect("http://localhost/login?message=tokenexpired");
            } else {
                User user = tokenInDB.getUser();
                if (user.getIsEnabled()) {
                    response.sendRedirect("http://localhost/login?message=exists");
                } else {
                    this.userService.enableAccount(user);
                    response.sendRedirect("http://localhost/login?message=success");
                }
            }
        } else {
            response.sendRedirect("http://localhost/login?message=invalidtoken");
        }
    }

}