package com.example.unitech.exception;

public interface ExceptionConstants {
    String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED_EXCEPTION";
    String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occurred";

    String USER_NOT_FOUND_CODE = "USER_NOT_FOUND";
    String USER_NOT_FOUND_MESSAGE = "User with id: %s not found";
    String USER_NOT_FOUND_BY_PIN_MESSAGE = "User with pin: %s not found";

    String ACCOUNT_NOT_FOUND_CODE = "ACCOUNT_NOT_FOUND";
    String ACCOUNT_NOT_FOUND_MESSAGE = "Account with id: %s not found";

    String SAME_STATUS_CODE = "SAME_STATUS_CODE";
    String SAME_STATUS_MESSAGE = "You can not change status because it is already : %s";

    String USER_ALREADY_REGISTERED_CODE = "USER_ALREADY_REGISTERED_CODE";
    String USER_ALREADY_REGISTERED_MESSAGE = "User with pin: %s is already registered";

    String ACCOUNT_ALREADY_EXISTS_CODE = "ACCOUNT_ALREADY_EXISTS_CODE";
    String ACCOUNT_ALREADY_EXISTS_MESSAGE = "Account with account number : %s already exists";

    String WRONG_CREDENTIALS_CODE = "WRONG_CREDENTIALS";
    String WRONG_CREDENTIALS_MESSAGE = "User doesn't match with given credentials!";

    String SAME_ACCOUNT_CODE = "SAME_STATUS_CODE";
    String SAME_ACCOUNT_MESSAGE = "You can not transfer money to the same account";

    String NO_ENOUGH_MONEY_CODE = "NO_ENOUGH_MONEY_CODE";
    String NO_ENOUGH_MONEY_MESSAGE = "There is no enough money for transfer";

    String INVALID_DATA_CODE = "INVALID_DATA_CODE";
    String INVALID_DATA_MESSAGE = "Invalid date or data";

    String INVALID_PAIR_CODE = "INVALID_PAIR_CODE";
    String INVALID_PAIR_MESSAGE = "Invalid date or currency";

    String NO_ACTIVE_ACCOUNT_FOUND_CODE = "NO_ACTIVE_ACCOUNT_FOUND_CODE";
    String NO_ACTIVE_ACCOUNT_FOUND_MESSAGE = "No active account found for user with id: %s";


}
