package com.xuegao.educloud.system.client.params.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 11:20
 * @Description:
 */
@Data
@Accessors(chain = true)
public class GradeDTO {

    /** 年级名称 */
    @NotBlank(message = "年级名称不能为空")
    private String gradeName;

    /** 父级ID */
    private Integer parentId;

    /** 排序*/
    private Integer sort;
}
