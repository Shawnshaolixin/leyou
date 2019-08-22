package com.leyou.common.enums;

public enum ExceptionEnum {
    PRICE_NOT_BE_IS_NULL(400,"价格不能为空"),
    BRAND_NOT_FOUND(404,"品牌没有找到"),
    UPLOAD_FILE_ERROR(500,"文件上传错误"),
    INVLID_FILE_TYPE(400,"无效的文件类型"),
    CATEGORY_NOT_FOUNT(404,"类别没有找到"),
    CATEGORY_ADD_FAILED(500,"类别新增失败")
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
