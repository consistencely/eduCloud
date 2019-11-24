package com.xuegao.educloud.common.exception;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xuegao.educloud.common.exception.enums.CommonExceptionEnum;
import com.xuegao.educloud.common.exception.resource.ErrorResource;
import com.xuegao.educloud.common.response.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:04
 * @Description: 对异常进行拦截然后封装到响应体
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 拦截无效参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        //TODO
        logger.warn("参数异常：{}",message);
        return R.fail(CommonExceptionEnum.INVALID_PARAM.getCode(),null,message);
    }

    /**
     * 拦截业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<?> handleServiceException(ServiceException e) {
        logger.warn("业务异常：{}",e.getMessage());
        return R.fail(e.getCode(),null,e.getMessage());
    }

    /**
     * 拦截其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handleException(Exception e) {
        logger.error("系统错误：",e);
        return R.fail("系统错误");
    }
}

