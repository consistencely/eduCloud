package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.server.service.ICampusDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zWei
 * @Date: 2019/11/18 15:40
 * @Description:
 */
@RestController
@RequestMapping
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
    @PostMapping("/campusDept")
    public R saveCampusDept(@RequestBody CampusDeptDTO campusDeptDTO) {
        if (campusDeptDTO.getParentId() == null || campusDeptDTO.getCampusId() == null) {
            return R.fail("参数有误");
        }
        if (StrUtil.isEmpty(campusDeptDTO.getDepartmentName())) {
            return R.fail("部门名称不能为空");
        }
        Integer success = campusDeptService.saveDepartment(campusDeptDTO);
        if (success > 0) {
            return R.ok();
        } else {
            return R.fail("保存失败");
        }
    }

    /**
     * 修改部门
     *
     * @param campusDeptDTO
     * @return
     */
    @PutMapping("/campusDept")
    public R updateCampusDept(@RequestBody CampusDeptDTO campusDeptDTO) {

        //参数校验
        Integer departmentId = campusDeptDTO.getDepartmentId();
        if (departmentId == null) {
            return R.fail("部门ID为空");
        }
        CampusDepartment campusInfo = campusDeptService.getById(departmentId);
        if (campusInfo == null) {
            return R.fail("部门信息不存在");
        }

        if (StrUtil.isEmpty(campusDeptDTO.getDepartmentName())) {
            return R.fail("部门名称不能为空");
        }
        Integer count = campusDeptService.updateCampus(campusDeptDTO);
        if (count > 0) {
            return R.ok();
        } else {
            return R.fail("修改失败");
        }
    }

    /**
     * 查询部门(树形结构)
     *
     * @return
     */
    @GetMapping("/campusDeptTree/{campusId}")
    public R campusDeptTreeList(@PathVariable("campusId") Integer campusId) {
        List<CampusDeptDTO> campusDepartmentList = campusDeptService.getDeptTreeByCampusId(campusId);
        return R.ok(campusDepartmentList);
    }

    /**
     * 查询部门
     *
     * @return
     */
    @GetMapping("/campusDept/{campusId}")
    public R campusDeptInfoList(@PathVariable("campusId") Integer campusId) {
        List<CampusDepartment> campusDepartmentList = campusDeptService.getDeptByCampusId(campusId);
        return R.ok(campusDepartmentList);
    }

    /**
     * 批量删除部门信息
     *
     * @param deptCampusMap 部门ID数组
     * @return
     */
    @DeleteMapping("/campusDepts")
    public R batchDeletecampusDept(@RequestBody Map<String, List<Integer>> deptCampusMap) {
        List<Integer> campusDeptIds = deptCampusMap.get("campusDeptIds");
        if (campusDeptIds == null || campusDeptIds.size() == 0) {
            return R.fail("请选择部门");
        }
        campusDeptService.removeByIds(campusDeptIds);
        return R.ok();
    }
}
