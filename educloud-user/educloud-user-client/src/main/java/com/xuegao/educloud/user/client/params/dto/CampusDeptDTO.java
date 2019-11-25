package com.xuegao.educloud.user.client.params.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * @Auther: ZWei
 * @Date: 2019/11/18 14:47
 * @Description: 部门实体类
 */
@Data
@Accessors(chain = true)
public class CampusDeptDTO {

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
     * 子节点
     */
    private List<CampusDeptDTO> children;
}
