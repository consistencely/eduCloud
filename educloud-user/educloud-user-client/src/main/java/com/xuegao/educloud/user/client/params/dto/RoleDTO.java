package com.xuegao.educloud.user.client.params.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

}
