package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;

import java.util.List;

/**
 * @Auther: ZWei
 * @Date: 2019/11/18 16:42
 * @Description:
 */
public interface ICampusDepartmentService extends IService<CampusDepartment> {

    /**
     * 保存部门
     *
     * @param campusDeptDTO
     * @return
     */
    Integer saveDepartment(CampusDeptDTO campusDeptDTO);

    /**
     * 修改部门
     *
     * @param campusDeptDTO
     * @return
     */
    Integer updateCampus(CampusDeptDTO campusDeptDTO);

    /**
     * 查询部门(封装的树形数据)
     *
     * @param campusId
     * @return
     */
    List<CampusDeptDTO> getDeptTreeByCampusId(Integer campusId);

    /**
     * 查询部门
     * @param campusId
     * @return
     */
    List<CampusDepartment> getDeptByCampusId(Integer campusId);
}
