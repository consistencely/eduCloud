package com.xuegao.educloud.system.client.params.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 10:09
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SubjectDTO {

    /** 学科名称 */
    @NotBlank(message = "学科名称不能为空")
    private String subjectName;

    /** 排序 */
    private Integer sort;

}
