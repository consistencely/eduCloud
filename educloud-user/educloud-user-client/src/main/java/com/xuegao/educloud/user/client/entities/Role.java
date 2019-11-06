package com.xuegao.educloud.user.client.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:14
 * @Description: 角色实体类
 */
@Data
@Accessors(chain = true)
@TableName("educloud_role")
public class Role {

    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

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

    /**
     * 是否删除（0：未删除，1：已删除）
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(select = false)
    private Byte isDel;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;


}
