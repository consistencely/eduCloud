package com.xuegao.educloud.common.exception;

import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:09
 * @Description:
 */
public class InvalidRequestException extends RuntimeException {

    @Getter
    private Integer code;

    public InvalidRequestException(String message){
        super(message);
    }

    public InvalidRequestException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
