package com.leyou.common.enums;

public enum ExceptionEnum {
    PRICE_NOT_BE_IS_NULL(400,"价格不能为空")
    ;
    private int status;

      ExceptionEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private String msg;
}
