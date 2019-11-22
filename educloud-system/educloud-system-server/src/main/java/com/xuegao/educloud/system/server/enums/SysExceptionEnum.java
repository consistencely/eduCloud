package com.xuegao.educloud.system.server.enums;

import com.xuegao.educloud.common.exception.enums.BaseExceptionEnum;
import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description:
 */
public enum SysExceptionEnum implements BaseExceptionEnum {

    NOT_FOUND(404,"资源未找到"),
    INVALID_PARAM(400,"参数不合法")
    ;

    SysExceptionEnum(int code, String message) {
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
        return this.getMessage();
    }
}
