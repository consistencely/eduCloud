package com.xuegao.educloud.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 17:19
 * @Description:
 */
public class Result<T> extends ResponseEntity<T> {


    private Result(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static <T> Result<T> success() {
        return restResult(null,null,HttpStatus.OK);
    }

    public static <T> Result<T> success(T body) {
        return restResult(body,null,HttpStatus.OK);
    }

    public static <T> Result<T> restResult(HttpStatus status) {
        return restResult(null,null,status);
    }

    public static <T> Result<T> restResult(T body, HttpStatus status) {
        return restResult(body,null,status);
    }

    private static <T> Result<T> restResult(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        return new Result<T>(body,headers,status);
    }
}
