package com.xuegao.educloud.common.exception;

import com.xuegao.educloud.common.exception.enums.CommonExceptionEnum;
import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:07
 * @Description:
 */
public class ResourceNoFoundException extends  ServiceException {


    public ResourceNoFoundException(){
        super(CommonExceptionEnum.NOT_FOUND);
    }

    public ResourceNoFoundException(String message) {
        super(CommonExceptionEnum.NOT_FOUND.getCode(), message);
    }
}
