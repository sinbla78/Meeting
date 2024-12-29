package com.example.decofolio.domain.user.exception;

import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;

public class UserAlreadyExistsException extends CustomException {

    public static final UserAlreadyExistsException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(ErrorCode.USER_EXISTS);
    }
}
