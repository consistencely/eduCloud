package com.xuegao.educloud.common.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果主体
 *
 */
@Data
public class Result implements Serializable {
    /**
     * 状态:200 为正常
     */
    private int code;

    private Object data;

    private String message;

    private boolean success;

    private Result(boolean success, int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static Result success() {
        return new Result(true,200, "", "SUCCESS");
    }

    public static Result success(Object data,String message) {
        return new Result(true,200, data, message);
    }

    public static Result success(Object data) {
        return new Result(true,200, data,"SUCCESS");
    }


    public static Result fail(int code,String message){
        return new Result(false,code,null,message);
    }

    public static Result fail(String message){
        return new Result(false,500,null,message);
    }

    public static Result fail(int code,Object data,String message){
        return new Result(false,code,data,message);
    }
}
