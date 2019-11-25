package com.xuegao.educloud.common.response;

import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 16:05
 * @Description: 处理controller方法的返回值，封装成{@link R}响应体
 */
@Slf4j
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info(JSONUtil.toJsonStr(o));
        if(o instanceof R){
            return o;
        }
        if(o instanceof Boolean){
            return (boolean)o ? R.ok() : R.fail("ERROR");
        }
        return R.ok(o);
    }
}
