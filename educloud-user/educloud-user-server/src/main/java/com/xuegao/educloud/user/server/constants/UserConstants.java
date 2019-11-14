package com.xuegao.educloud.user.server.constants;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 16:14
 * @Description: 用户中心常量
 */
public interface UserConstants {

    /**
     * 密码正则（6到16位数字或字母）
     */
    String PATTERN_PWD = "^[0-9a-zA-Z]{6,16}$";
    /**
     * 默认头像地址
     */
    String DEFAULT_AVATAR = "https://avatar.csdnimg.cn/4/0/3/3_qq_39507276_1552281384.jpg"; //todo

    /**
     * 注册来源-PC端
     */
    byte REGISTER_FROM_PC = 1;

    /**
     * 注册来源-APP端
     */
    byte REGISTER_FROM_APP = 2;

    /**
     * 注册来源-手机浏览器端
     */
    byte REGISTER_FROM_WAP = 3;

    /**
     * 注册来源-管理员后台创建
     */
    byte REGISTER_FROM_ADMIN = 4;

    /**
     * 账号状态-注销
     */
    byte USER_STATUS_DEL = 0;

    /**
     * 账号状态-正常
     */
    byte USER_STATUS_NORMAL = 1;

    /**
     * 账号状态-冻结
     */
    byte USER_STATUS_LOCK = 2;

    /**
     * 用户有效类型 - 永久有效
     */
    byte VALID_TYPE_PERPETUAL = 1;

    /**
     * 用户有效类型 - 过期失效
     */
    byte VALID_TYPE_OVERDUE = 2;

    /**
     * 用户有效类型 - 区间有效
     */
    byte VALID_TYPE_SCOPE = 3;
}
