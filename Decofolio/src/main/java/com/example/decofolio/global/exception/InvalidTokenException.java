package com.example.decofolio.global.exception;

import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;

public class InvalidTokenException extends CustomException {

    public static final InvalidTokenException EXCEPTION =
            new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_JWT);
    }
}
