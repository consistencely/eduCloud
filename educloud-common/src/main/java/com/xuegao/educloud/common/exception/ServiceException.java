package com.xuegao.educloud.common.exception;

import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/21 15:39
 * @Description: 业务异常类
 */
public class ServiceException extends RuntimeException {
    @Getter
    private Integer code;

    @Getter
    private String message;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
