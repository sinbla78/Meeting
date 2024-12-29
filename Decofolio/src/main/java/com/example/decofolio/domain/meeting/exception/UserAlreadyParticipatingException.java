package com.example.decofolio.domain.meeting.exception;

import com.example.decofolio.domain.user.exception.UserAlreadyExistsException;
import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;

public class UserAlreadyParticipatingException extends CustomException {

    public static final UserAlreadyParticipatingException EXCEPTION =
            new UserAlreadyParticipatingException();

    private UserAlreadyParticipatingException() {
        super(ErrorCode.USER_ALREADY_PARTICIPATING);
    }
}
