package com.xuegao.educloud.user.client.params.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/6 09:09
 * @Description:
 */
@Data
@Accessors(chain = true)
public class RoleDTO {

     private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 有效类型（1：永久有效，2：到期失效，3：区间有效）
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
}
