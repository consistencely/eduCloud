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
     * 昵称
     */
    private String nickname;

    /**
     * 账号状态（0：注销，1：正常，2：冻结）
     */
    private Byte status;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色
     */
    private String roleName;

    /**
     * 生源归属
     */
    private String sourceName;

    /**
     * 所属机构
     */
    private String campusName;

    /**
     * 年级
     */
    private String gradeName;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 生源ID
     */
    private Integer sourceId;

    /**
     * 校区ID
     */
    private Integer campusId;


}
