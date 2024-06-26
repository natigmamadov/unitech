package com.example.unitech.service.concrete;

import com.example.unitech.dao.repository.UserRepository;
import com.example.unitech.exception.AlreadyExistsException;
import com.example.unitech.exception.NotFoundException;
import com.example.unitech.exception.WrongCredentialsException;
import com.example.unitech.model.request.LoginUserRequest;
import com.example.unitech.model.request.RegisterUserRequest;
import com.example.unitech.service.abstraction.AuthService;
import com.example.unitech.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.unitech.exception.ExceptionConstants.*;
import static com.example.unitech.mapper.UserMapper.USER_MAPPER;


@RequiredArgsConstructor
@Service
public class AuthServiceHandler implements AuthService {
    private final UserRepository userRepository;
    private final AuthUtil authUtil;


    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        String pin = registerUserRequest.getPin();

        if (userRepository.findByPinIgnoreCase(pin).isPresent()) {
            throw new AlreadyExistsException(
                    USER_ALREADY_REGISTERED_CODE,
                    String.format(USER_ALREADY_REGISTERED_MESSAGE, pin));
        }

        registerUserRequest.setPassword(authUtil.hashPassword(registerUserRequest.getPassword()));
        userRepository.save(USER_MAPPER.buildUserEntity(registerUserRequest));
    }

    @Override
    public void authUser(LoginUserRequest loginUserRequest) {
        String pin = loginUserRequest.getPin();

        userRepository.findByPinIgnoreCase(pin)
                .ifPresentOrElse(userEntity -> {
                    if (!authUtil.verifyPassword(loginUserRequest.getPassword(), userEntity.getPassword())) {
                        throw new WrongCredentialsException(WRONG_CREDENTIALS_CODE,
                                WRONG_CREDENTIALS_MESSAGE);
                    }
                }, () -> {
                    throw new NotFoundException(USER_NOT_FOUND_CODE,
                            String.format(USER_NOT_FOUND_BY_PIN_MESSAGE, pin));
                });

    }

}
