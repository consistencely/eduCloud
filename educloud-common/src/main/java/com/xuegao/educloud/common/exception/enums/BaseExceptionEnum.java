package com.xuegao.educloud.common.exception.enums;

import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.exception.resource.ErrorResource;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description:
 */
public interface BaseExceptionEnum {

    Integer getCode();

    String getMessage();

    default String toJSON(){
        return JSONUtil.toJsonStr(this);
    }

    default ErrorResource toErrorBean(){
        return new ErrorResource(this.getCode(),this.getMessage());
    }

}
