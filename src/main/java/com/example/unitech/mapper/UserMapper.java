package com.example.unitech.mapper;


import com.example.unitech.dao.entity.UserEntity;
import com.example.unitech.model.request.RegisterUserRequest;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(RegisterUserRequest registerUserRequest) {
        return UserEntity.builder()
                .pin(registerUserRequest.getPin())
                .password(registerUserRequest.getPassword())
                .build();
    }

}
