package com.xuegao.educloud.user.client.params.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: LIM
 * @Date: 2019/11/11 15:45
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserQuery {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年级
     */
    private int[] gradeIds;

    /**
     * 生源
     */
    private int[] sourceIds;

    /**
     * 角色
     */
    private int[] roleIds;

    /**
     * 机构
     */
    private int[] organIds;

    /**
     * 状态
     */
    private int[] statusCodes;

    /**
     * 注册时间排序类型（1：升序，2：降序）
     */
    private Integer registerSort;
}
