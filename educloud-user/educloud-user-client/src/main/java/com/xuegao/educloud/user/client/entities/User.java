package com.xuegao.educloud.user.client.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 10:34
 * @Description:
 */
@Data
@Accessors(chain = true)
@TableName("educloud_user")
public class User {


    /**
     * 用户ID
     */
    @TableId
    private Long userId;

    /**
     * 系统账号
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 生源ID
     */
    private Integer sourceId;

    /**
     * 机构ID
     */
    private Integer organizationId;

    /**
     * 注册来源（1：PC端，2：移动端，3：手机浏览器，4：管理员后台创建）
     */
    private Byte registerFrom;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 账号状态（0：注销，1：正常，2：冻结）
     */
    private Byte status;

    /**
     * 随机字符串
     */
    private String uuid;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
