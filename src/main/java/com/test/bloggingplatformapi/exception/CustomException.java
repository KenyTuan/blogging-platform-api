package com.test.bloggingplatformapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public String errCode;

    public String errMsg;

//    public CustomException(String errCode, String errMsg) {
//        super(errMsg);
//        this.errCode = errCode;
//        this.errMsg = errMsg;
//    }
}
