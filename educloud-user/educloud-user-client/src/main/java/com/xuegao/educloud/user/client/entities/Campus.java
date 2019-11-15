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
 * @Date: 2019/11/12 15:47
 * @Description: 校区实体类
 */
@Data
@Accessors(chain = true)
@TableName("educloud_campus")
public class Campus {

    /**
     * 校区ID
     */
    @TableId
    private Integer campusId;

    /**
     * 校区名称
     */
    private String campusName;

    /**
     * 负责人
     */
    private String person;

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
