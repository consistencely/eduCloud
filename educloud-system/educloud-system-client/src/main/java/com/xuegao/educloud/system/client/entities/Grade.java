package com.xuegao.educloud.system.client.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:14
 * @Description: 学段（年级）实体
 */
@Data
@Accessors(chain = true)
@TableName("educloud_grade")
public class Grade {

    @TableId
    private Integer gradeId;

    /** 学科名称 */
    private String gradeName;

    /** 排序*/
    private Integer sort;

    /** 0：正常，1：删除 */
    @TableLogic
    @TableField(select = false)
    private Byte isDel;

    /** 修改时间 */
    private Date modifyTime;

    /** 创建时间 */
    private Date createTime;
}
