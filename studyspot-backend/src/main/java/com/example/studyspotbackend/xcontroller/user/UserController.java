package com.example.studyspotbackend.xcontroller.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.models.user.helpers.UserInfoDto;
import com.example.studyspotbackend.models.user.helpers.UserRegisterDto;
import com.example.studyspotbackend.service.user.interfaces.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto){
        return this.userService.register(userRegisterDto)
                .map(user -> ResponseEntity.ok().body("User is registered successfully. Please check your email to finish registration."))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> listAll(){
        return this.userService.findAll();
    }

    @GetMapping("/details")
    public ResponseEntity<UserInfoDto> userDetails(HttpServletRequest request){
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


}