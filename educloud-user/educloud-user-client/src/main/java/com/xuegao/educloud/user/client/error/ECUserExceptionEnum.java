package com.xuegao.educloud.user.client.error;

import com.xuegao.educloud.common.exception.enums.BaseExceptionEnum;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:43
 * @Description: 用户服务异常枚举类
 *
 *  错误码格式为ABBCC:
 *      A：微服务ID（1-系统服务，2-用户...查看help.txt）
 *      B：服务内模块ID，从01自增
 *      C：具体错误编号，从01自增
 *
 *
 */
public enum ECUserExceptionEnum implements BaseExceptionEnum {

    /**
     * 角色
     */
    ROLE_NOT_FOUND(20101,"角色不存在"),
    ROLE_NOTALLOW_DEL(20102,"角色已配置，不允许删除"),

    /**
     * 生源
     */
    SOURCE_NOT_FOUND(20201,"生源不存在"),
    SOURCE_NOTALLOW_DEL(20201,"生源已配置，不允许删除"),

    /**
     * 用户
     */
    USER_NOT_FOUND(20301,"用户不存在"),
    PHONE_EXIST(20302,"手机号码已被注册"),
    USER_DEL(20303,"用户已注销,操作失败"),

    /**
     * 校区
     */
    CAMPUS_NOT_FOUND(20401,"校区不存在"),

    /**
     * 其他
     */
    INCLUDE_INVALID_GRADE(29901,"内含无效年级"),


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
