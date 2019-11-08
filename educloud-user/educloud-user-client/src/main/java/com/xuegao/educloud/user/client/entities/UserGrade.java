package com.xuegao.educloud.user.client.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 10:46
 * @Description:
 */
@Data
@Accessors(chain = true)
@TableName("educloud_user_grade")
public class UserGrade {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 年级ID
     */
    private Integer gradeId;

    /**
     * 关联时间
     */
    private Date createTime;
}
