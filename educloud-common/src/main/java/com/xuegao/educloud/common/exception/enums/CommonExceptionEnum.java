package com.xuegao.educloud.common.exception.enums;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description:
 */
public enum CommonExceptionEnum implements BaseExceptionEnum {

    INVALID_PARAM(40000,"请求参数非法"),
    NOT_ALLOW(40300,"拒绝访问"),
    NOT_FOUND(40400,"资源不存在"),
    SERVER_ERROR(50000,"服务器内部错误"),
    HANDLE_ERROR(50100,"资源操作失败"),
    INTERFACE_CLOSED(60000,"接口暂时无法访问")
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
