package com.xuegao.educloud.user.server.error;

import com.xuegao.educloud.common.exception.enums.BaseExceptionEnum;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description: 用户服务异常枚举类
 */
public enum ECUserExceptionEnum implements BaseExceptionEnum {

    /**
     * 角色
     */
    ROLE_NOT_FOUND(404,"角色不存在"),
    ROLE_NOTALLOW_DEL(500,"角色已配置，不允许删除"),

    /**
     * 生源
     */
    SOURCE_NOT_FOUND(404,"生源不存在"),
    SOURCE_NOTALLOW_DEL(500,"生源已配置，不允许删除"),

    /**
     * 用户
     */
    USER_NOT_FOUND(404,"用户不存在"),
    PHONE_EXIST(500,"手机号码已被注册"),
    USER_DEL(500,"用户已注销,操作失败"),

    /**
     * 校区
     */
    CAMPUS_NOT_FOUND(404,"校区不存在"),

    /**
     * 其他
     */
    INCLUDE_INVALID_GRADE(500,"内含无效年级"),


    ;

    ECUserExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
