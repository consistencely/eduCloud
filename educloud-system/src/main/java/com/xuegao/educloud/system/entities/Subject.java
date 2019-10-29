package com.xuegao.educloud.system.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:14
 * @Description: 学科实体
 */
@Data
@TableName("educloud_subject")
public class Subject {

    @TableId
    private Integer id;

    /** 学科名称 */
    private String name;

    /** 排序（倒序）*/
    private Integer sort;

    /** 0：正常，1：删除 */
    @TableLogic
    private Byte isDel;

    /** 修改时间 */
    private Date modifyTime;

    /** 创建时间 */
    private Date createTime;
}
