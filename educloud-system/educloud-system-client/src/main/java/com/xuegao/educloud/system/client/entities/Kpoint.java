package com.xuegao.educloud.system.client.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/10/30 11:33
 * @Description: 考点实体
 */
@Data
@TableName("educloud_kpoint")
public class Kpoint {

    @TableId
    private Integer kpointId;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 考点名称
     */
    private String kpointName;

    /**
     * 学段ID
     */
    private Integer gradeId;

    /**
     * 学科ID
     */
    private Integer subjectId;

    /**
     * 第几级
     */
    private Integer level;

    /**
     * 是否删除（0：正常，1：删除）
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
