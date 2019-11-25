package com.xuegao.educloud.user.client.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: ZWei
 * @Date: 2019/11/18 14:47
 * @Description: 部门实体类
 */
@Data
@Accessors(chain = true)
@TableName("educloud_campus_department")
public class CampusDepartment {

    /**
     * 部门ID
     */
    @TableId
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 父级ID（0：根节点）
     */
    private Integer parentId;

    /**
     * 校区ID
     */
    private Integer campusId;

    /**
     * 第几级
     */
    private Integer level;

    /**
     * 节点路径（如：/1/7/10）
     */
    private String path;

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
