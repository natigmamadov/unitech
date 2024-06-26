package com.example.unitech.controller;

import com.example.unitech.model.request.LoginUserRequest;
import com.example.unitech.model.request.RegisterUserRequest;
import com.example.unitech.service.abstraction.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService securityService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public void registerUser(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        securityService.registerUser(registerUserRequest);
    }

    @PostMapping("/log-in")
    @ResponseStatus(NO_CONTENT)
    public void authUser(@RequestBody LoginUserRequest loginUserRequest) {
        securityService.authUser(loginUserRequest);
    }
}
