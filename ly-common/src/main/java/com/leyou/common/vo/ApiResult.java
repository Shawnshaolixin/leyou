package com.leyou.common.vo;

import lombok.Data;

@Data
public class ApiResult<T> {
    private Boolean result;
    private T data;
    private String message;

    public ApiResult(Boolean result, T data) {
        this.result = result;
        this.data = data;
        this.message ="操作成功";
    }
    public ApiResult(Boolean result){
        this.result =true;
        this.message = "操作成功";
    }

    public   ApiResult(Boolean result, T data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public static ApiResult ok(Object data){
        return new ApiResult(true,data);
    }
    public static ApiResult ok(){
        return  new ApiResult(true);
    }
    public static ApiResult error(String message){
        return new ApiResult(false,null,message);
    }
}
