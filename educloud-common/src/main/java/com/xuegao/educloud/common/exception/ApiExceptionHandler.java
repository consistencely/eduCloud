package com.xuegao.educloud.common.exception;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xuegao.educloud.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:04
 * @Description: 对异常进行拦截然后封装到响应体
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截404异常
     * @param e
     * @return
     */
    @ExceptionHandler(ResourceNoFoundException.class)
    public Result<?> handleNotFound(ResourceNoFoundException e) {
        ErrorResource errorResource = new ErrorResource(e.getCode(),e.getMessage());
        logger.error(errorResource.getMessage());
        return Result.restResult(errorResource, HttpStatus.NOT_FOUND);
    }

    /**
     * 拦截400异常
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidRequestException.class)
    public Result<?> handleInvalidRequest(InvalidRequestException e) {
        ErrorResource errorResource = new ErrorResource(e.getCode(),e.getMessage());
        logger.error(errorResource.getMessage());
        return Result.restResult(errorResource, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HystrixBadRequestException.class)
    public Result<?> hystrixBadRequestException(Exception e) {
        logger.error("HystrixBadRequestException：{}",e.getMessage());
        ErrorResource resource = null;
        if(StrUtil.isNotEmpty(e.getMessage())){
            resource = JSONUtil.toBean(e.getMessage(), ErrorResource.class);
        }
        return Result.success(resource);
    }

    /**
     * 拦截其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        logger.error("系统错误：",e);
        return Result.restResult(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

