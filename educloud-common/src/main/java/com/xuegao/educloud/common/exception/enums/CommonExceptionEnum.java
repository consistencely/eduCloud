package com.xuegao.educloud.common.exception.enums;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description:
 */
public enum CommonExceptionEnum implements BaseExceptionEnum {

    NOT_FOUND(404,"资源未找到"),
    INVALID_PARAM(400,"参数不合法"),
    SERVER_ERROR(500,"系统错误")
    ;

    CommonExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
