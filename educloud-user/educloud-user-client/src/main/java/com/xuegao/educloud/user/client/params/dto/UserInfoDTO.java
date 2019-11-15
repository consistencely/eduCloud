package com.xuegao.educloud.user.client.params.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 11:53
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserInfoDTO {

    /**
     * 用户ID
     */
    private Long userId;

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
     * 角色ID数组
     */
    private Integer[] roleIds;

    /**
     * 年级ID数组
     */
    private Integer[] gradeIds;


    /**
     * 生源ID
     */
    private Integer sourceId;

    /**
     * 校区ID
     */
    private Integer campusId;

    /**
     * 有效类型（1：永久有效，2：过期失效，3：区间有效）
     */
    private Byte validType;

    /**
     * 有效期开始时间
     */
    private Date validStart;

    /**
     * 有效期结束时间
     */
    private Date validEnd;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 详细地址
     */
    private String addrDetail;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 用户ID数组
     */
    private Long[] userIds;
}
