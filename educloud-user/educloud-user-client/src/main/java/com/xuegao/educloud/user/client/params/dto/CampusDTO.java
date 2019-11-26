package com.xuegao.educloud.user.client.params.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZWei
 * @Date: 2019/11/18 15:04
 * @Description:
 */
@Data
@Accessors(chain = true)
public class CampusDTO {

    /**
     * 校区ID
     */
    private Integer campusId;

    /**
     * 校区名称
     */
    @NotBlank(message = "校区名称不能为空")
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
     * 部门/班级
     */
    private String departmentNameStr;

    /**
     * 校区总人数
     */
    private Integer countNum;
}
