package com.example.unitech.model.request;

import com.example.unitech.config.validation.Password;
import com.example.unitech.config.validation.Pin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @Pin
    private String pin;

    @Password
    private String password;


}
