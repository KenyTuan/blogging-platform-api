package com.test.bloggingplatformapi.exception;


import lombok.Getter;

@Getter
public class GenericAlreadyException extends CustomException{
    public GenericAlreadyException(String errorMessage) {
        super(ErrorCode.DUPLICATE_RESOURCE.getErrCode(), errorMessage);
    }
}
