package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;

public class LyException extends RuntimeException {
    public LyException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
    public LyException(){

    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    private ExceptionEnum exceptionEnum;

}
