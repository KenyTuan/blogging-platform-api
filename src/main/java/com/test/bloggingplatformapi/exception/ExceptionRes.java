package com.test.bloggingplatformapi.exception;

import java.io.Serializable;
import java.time.Instant;

public record ExceptionRes(
        String code,
        String message,
        Integer status,
        String url,
        String reqMethod,
        Instant timestamp
) implements Serializable {
}
