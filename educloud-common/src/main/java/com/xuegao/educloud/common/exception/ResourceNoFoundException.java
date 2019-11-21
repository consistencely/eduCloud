package com.xuegao.educloud.common.exception;

import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:07
 * @Description:
 */
public class ResourceNoFoundException extends  ServiceException {


    public ResourceNoFoundException(){
        super(404, "找不到对应资源");
    }

    public ResourceNoFoundException(String message) {
        super(404, message);
    }
}
