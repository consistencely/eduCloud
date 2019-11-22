package com.xuegao.educloud.common.exception;

import com.xuegao.educloud.common.exception.enums.CommonExceptionEnum;
import lombok.Getter;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:09
 * @Description:
 */
public class InvalidRequestException extends ServiceException {


    public InvalidRequestException(){
        super(CommonExceptionEnum.INVALID_PARAM);
    }

    public InvalidRequestException(String message) {
        super(CommonExceptionEnum.INVALID_PARAM.getCode(), message);
    }


}
