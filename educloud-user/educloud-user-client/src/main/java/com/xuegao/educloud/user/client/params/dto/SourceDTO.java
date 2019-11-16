package com.xuegao.educloud.user.client.params.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: ZWei
 * @Date: 2019/11/14 15:41
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SourceDTO {

    /**
     * 生源ID
     */
    private Integer sourceId;

    /**
     * 生源名称
     */
    private String sourceName;

    /**
     * 报名方式
     */
    private String applyWay;

    /**
     * 负责人/推荐人
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
     * 已配置生源地人数
     */
    private Integer countNum;
}
