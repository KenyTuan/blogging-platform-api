package com.test.bloggingplatformapi.exception;

public class InvalidRefreshTokenException extends CustomException{
    public InvalidRefreshTokenException(String message) {
        super(ErrorCode.UNAUTHORIZED.getErrCode(), message);
    }
}
