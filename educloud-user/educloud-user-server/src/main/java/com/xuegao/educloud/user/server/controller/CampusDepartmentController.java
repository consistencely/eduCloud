package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.error.ECUserExceptionEnum;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.server.service.ICampusDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zWei
 * @Date: 2019/11/18 15:40
 * @Description:
 */
@RestController
@RequestMapping("/campusDepts")
@Slf4j
public class CampusDepartmentController {

    @Autowired
    private ICampusDepartmentService campusDeptService;

    /**
     * 新增部门
     *
     * @param campusDeptDTO
     * @return
     */
    @PostMapping
    public Boolean saveCampusDept(@Valid @RequestBody CampusDeptDTO campusDeptDTO) {
        return campusDeptService.saveDepartment(campusDeptDTO) > 0;
    }

    /**
     * 修改部门
     *
     * @param campusDeptDTO
     * @return
     */
    @PutMapping("/{departmentId}")
    public Boolean updateCampusDept(@PathVariable("departmentId") int departmentId, @Valid @RequestBody CampusDeptDTO campusDeptDTO) {
        CampusDepartment campusInfo = campusDeptService.getById(departmentId);
        if (campusInfo == null) {
            throw new ServiceException(ECUserExceptionEnum.CAMPUSDEPT_NOT_FOUND);
        }
        campusDeptDTO.setDepartmentId(departmentId);
        return campusDeptService.updateCampus(campusDeptDTO) > 0;
    }

    /**
     * 查询部门(树形结构)
     *
     * @return
     */
    @GetMapping("/deptTree/{campusId}")
    public List<CampusDeptDTO> campusDeptTreeList(@PathVariable("campusId") Integer campusId) {
        return campusDeptService.getDeptTreeByCampusId(campusId);
    }

    /**
     * 查询部门
     *
     * @return
     */
    @GetMapping("/{campusId}")
    public List<CampusDepartment> campusDeptInfoList(@PathVariable("campusId") Integer campusId) {
        return campusDeptService.getDeptByCampusId(campusId);
    }

    /**
     * 批量删除部门信息
     *
     * @param deptCampusMap 部门ID数组
     * @return
     */
    @DeleteMapping("/batch")
    public void batchDeletecampusDept(@RequestBody Map<String, List<Integer>> deptCampusMap) {
        List<Integer> campusDeptIds = deptCampusMap.get("campusDeptIds");
        if (campusDeptIds == null || campusDeptIds.size() == 0) {
            throw new InvalidRequestException("校区部门ID不存在");
        }
        campusDeptService.removeByIds(campusDeptIds);
    }
}
