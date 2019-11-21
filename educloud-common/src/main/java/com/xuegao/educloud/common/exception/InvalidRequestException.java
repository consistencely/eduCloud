package com.xuegao.educloud.common.exception;

import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:09
 * @Description:
 */
public class InvalidRequestException extends ServiceException {


    public InvalidRequestException(){
        super(400, "请求数据不完整或格式错误！");
    }

    public InvalidRequestException(String message) {
        super(400, message);
    }


}
