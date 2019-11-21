package com.xuegao.educloud.user.server.error;

import cn.hutool.json.JSONUtil;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xuegao.educloud.common.exception.resource.ErrorResource;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
            Integer errorCode = errorResource.getCode();
            //TODO
            if(errorCode != null){
                 exception = new HystrixBadRequestException(body);
            }
        }
        if (exception == null) {
            exception = super.decode(methodKey, response);
        }
        return exception;
    }
}
