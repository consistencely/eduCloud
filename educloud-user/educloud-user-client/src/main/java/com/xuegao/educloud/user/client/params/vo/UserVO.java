package com.xuegao.educloud.user.client.params.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/11 15:53
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色
     */
    private String role;

    /**
     * 生源归属
     */
    private String source;

    /**
     * 所属机构
     */
    private String organ;

    /**
     * 年级
     */
    private String grade;



}
