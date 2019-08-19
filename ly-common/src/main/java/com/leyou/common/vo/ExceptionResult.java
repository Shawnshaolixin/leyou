package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;

public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status=em.getStatus();
        this.message=em.getMsg();
        this.timestamp=System.currentTimeMillis();
    }
}
