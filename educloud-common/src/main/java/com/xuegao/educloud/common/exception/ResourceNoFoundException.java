package com.xuegao.educloud.common.exception;

import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:07
 * @Description:
 */
public class ResourceNoFoundException extends  RuntimeException {

    @Getter
    private Integer code;

    public ResourceNoFoundException(String message){
        super(message);
    }

    public ResourceNoFoundException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
