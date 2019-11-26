package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.error.ECUserExceptionEnum;
import com.xuegao.educloud.user.client.params.dto.CampusDTO;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import com.xuegao.educloud.user.server.service.ICampusDepartmentService;
import com.xuegao.educloud.user.server.service.ICampusService;
import com.xuegao.educloud.user.server.service.ISourceService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZWei
 * @Date: 2019/11/18 15:11
 * @Description:
 */
@RestController
@RequestMapping("/campuses")
@Slf4j
public class CampusController {

    @Autowired
    private ICampusService campusService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICampusDepartmentService campusDeptService;

    /**
     * 新增校区
     *
     * @param campusDTO
     * @return
     */
    @PostMapping
    public Boolean saveCampus(@Valid @RequestBody CampusDTO campusDTO) {
        return campusService.saveCampus(campusDTO) > 0;
    }

    /**
     * 修改校区
     *
     * @param campusId
     * @param campusDTO
     * @return
     */
    @PutMapping("/{campusId}")
    public Boolean updateCampus(@PathVariable("campusId") int campusId, @Valid @RequestBody CampusDTO campusDTO) {
        Campus campusInfo = campusService.getById(campusId);
        if (campusInfo == null) {
            throw new ServiceException(ECUserExceptionEnum.CAMPUS_NOT_FOUND);
        }
        campusDTO.setCampusId(campusId);
        return campusService.updateCampus(campusDTO) > 0;
    }

    /**
     * 批量删除校区信息
     *
     * @param campusMap 校区ID数组
     * @return
     */
    @DeleteMapping("/batch")
    public void batchDeleteCampus(@RequestBody Map<String, List<Integer>> campusMap) {
        List<Integer> campusIds = campusMap.get("campusIds");
        if (campusIds == null || campusIds.size() == 0) {
            throw new InvalidRequestException("校区ID不能为空");
        }
        for (Integer campusId : campusIds) {
            Campus campusInfo = campusService.getById(campusId);
            if (campusInfo == null) {
                throw new ServiceException(ECUserExceptionEnum.CAMPUS_NOT_FOUND);
            }
            //判断是否存在已配置的校区
            List<User> userList = userService.getUserByCampusId(campusId);
            if (userList != null && userList.size() > 0) {
                throw new ServiceException(ECUserExceptionEnum.CAMPUS_NOTALLOW_DEL);
            }
        }
        campusService.removeByIds(campusIds);
    }

    /**
     * 分页查询(包括部门)校区
     *@param pageNum
     * @param pageSize
     * @param campusDTO
     * @return
     */
/*    @GetMapping("/pageTree")
    public IPage<CampusDTO> campusTreeInfoPage(@RequestParam(value = "pageNum", defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                               @ModelAttribute("campusDTO") CampusDTO campusDTO) {
        Page<CampusDTO> page = new Page<CampusDTO>().setCurrent(pageNum).setSize(pageSize);
        IPage<CampusDTO> sourcePage = campusService.getCampusDeptTreePage(page, campusDTO);
        return sourcePage;
    }*/

    /**
     * 分页查询校区
     *
     * @param pageNum
     * @param pageSize
     * @param campusDTO
     * @return
     */
    @GetMapping("/page")
    public IPage<CampusDTO> campusInfoPage(@RequestParam(value = "pageNum", defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                           @ModelAttribute("campusDTO") CampusDTO campusDTO) {
        Page<CampusDTO> page = new Page<CampusDTO>().setCurrent(pageNum).setSize(pageSize);
        IPage<CampusDTO> sourcePage = campusService.getCampusPage(page, campusDTO);
        return sourcePage;
    }

    /**
     * 查询校区
     *
     * @return
     */
    @GetMapping
    public List<Campus> campusInfoList() {
        return campusService.getCampusList();
    }

    /**
     * 新增部门
     *
     * @param campusDeptDTO
     * @return
     */
    @PostMapping("/depts")
    public Boolean saveCampusDept(@RequestBody CampusDeptDTO campusDeptDTO) {
        return campusDeptService.saveDepartment(campusDeptDTO) > 0;
    }

    /**
     * 修改部门
     *
     * @param departmentId
     * @param campusDeptDTO
     * @return
     */
    @PutMapping("/depts/{departmentId}")
    public Boolean updateCampusDept(@PathVariable("departmentId") int departmentId, @RequestBody CampusDeptDTO campusDeptDTO) {
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
   /* @GetMapping("/depts/deptTree/{campusId}")
    public List<CampusDeptDTO> campusDeptTreeList(@PathVariable("campusId") Integer campusId) {
        return campusDeptService.getDeptTreeByCampusId(campusId);
    }*/

    /**
     * 查询部门
     *
     * @param campusId
     * @return
     */
    @GetMapping("/depts/{campusId}")
    public List<CampusDepartment> campusDeptInfoList(@PathVariable("campusId") Integer campusId) {
        return campusDeptService.getDeptByCampusId(campusId);
    }

    /**
     * 批量删除部门信息
     *
     * @param deptCampusMap 部门ID数组
     * @return
     */
    @DeleteMapping("/depts/batch")
    public void batchDeletecampusDept(@RequestBody Map<String, List<Integer>> deptCampusMap) {
        List<Integer> campusDeptIds = deptCampusMap.get("campusDeptIds");
        if (campusDeptIds == null || campusDeptIds.size() == 0) {
            throw new InvalidRequestException("校区部门ID不存在");
        }
        for (Integer campusDeptId : campusDeptIds) {
            CampusDepartment campusInfo = campusDeptService.getById(campusDeptId);
            if (campusInfo == null) {
                throw new ServiceException(ECUserExceptionEnum.CAMPUSDEPT_NOT_FOUND);
            }
        }
        campusDeptService.removeByIds(campusDeptIds);
    }

}
