package com.example.unitech.service.abstraction;


import com.example.unitech.model.request.LoginUserRequest;
import com.example.unitech.model.request.RegisterUserRequest;

public interface AuthService {
    void registerUser(RegisterUserRequest registerUserRequest);

    void authUser(LoginUserRequest loginUserRequest);

}
