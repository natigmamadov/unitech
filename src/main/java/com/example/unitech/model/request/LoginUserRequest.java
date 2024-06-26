package com.example.unitech.model.request;


import com.example.unitech.config.validation.Password;
import com.example.unitech.config.validation.Pin;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {

    @Pin
    private String pin;

    @Password
    private String password;

}