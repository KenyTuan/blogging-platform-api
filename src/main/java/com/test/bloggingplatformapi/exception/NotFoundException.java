package com.test.bloggingplatformapi.exception;

public class NotFoundException extends CustomException{

    public NotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND.getErrCode(), message);
    }

}
