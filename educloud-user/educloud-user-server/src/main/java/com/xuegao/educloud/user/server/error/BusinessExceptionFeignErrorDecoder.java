package com.xuegao.educloud.user.server.error;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xuegao.educloud.common.exception.ErrorResource;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/20 14:33
 * @Description:
 */
@Slf4j
@Component
public class BusinessExceptionFeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = null;
        String body = null;
        try {
            body = Util.toString(response.body().asReader());
        } catch (IOException e) {
            exception = e;
        }
        if(body != null){
            //log.info("【FeignErrorDecoder】body：{}",body);
            ErrorResource errorResource = JSONUtil.toBean(body, ErrorResource.class);
            Integer errorCode = errorResource.getErrorCode();
            if(errorCode != null){
                // exception = new HystrixBadRequestException(body);
                return null;
            }
        }
        if (exception == null) {
            exception = super.decode(methodKey, response);
        }
        return exception;
    }
}
