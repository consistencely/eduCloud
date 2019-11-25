package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.CampusDTO;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import com.xuegao.educloud.user.server.service.ICampusService;
import com.xuegao.educloud.user.server.service.ISourceService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: ZWei
 * @Date: 2019/11/18 15:11
 * @Description:
 */
@RestController
@RequestMapping
@Slf4j
public class CampusController {

    @Autowired
    private ICampusService campusService;
    @Autowired
    private IUserService userService;

    /**
     * 新增校区
     *
     * @param campusDTO
     * @return
     */
    @PostMapping("/campus")
    public R saveCampus(@RequestBody CampusDTO campusDTO) {
        if (StrUtil.isEmpty(campusDTO.getCampusName())) {
            return R.fail("校区名称不能为空");
        }
        Integer success = campusService.saveCampus(campusDTO);
        if (success > 0) {
            return R.ok();
        } else {
            return R.fail("保存失败");
        }
    }

    /**
     * 修改校区
     *
     * @param campusDTO
     * @return
     */
    @PutMapping("/campus")
    public R updateCampus(@RequestBody CampusDTO campusDTO) {

        //参数校验
        Integer campusId = campusDTO.getCampusId();
        if (campusId == null) {
            return R.fail("校区ID为空");
        }
        Campus campusInfo = campusService.getById(campusId);
        if (campusInfo == null) {
            return R.fail("校区信息不存在");
        }
        if (StrUtil.isEmpty(campusDTO.getCampusName())) {
            return R.fail("校区名称不能为空");
        }
        Integer count = campusService.updateCampus(campusDTO);
        if (count > 0) {
            return R.ok();
        } else {
            return R.fail("修改失败");
        }
    }

    /**
     * 批量删除校区信息
     *
     * @param campusMap 校区ID数组
     * @return
     */
    @DeleteMapping("/campuss")
    public R batchDeleteCampus(@RequestBody Map<String, List<Integer>> campusMap) {
        List<Integer> campusIds = campusMap.get("campusIds");
        if (campusIds == null || campusIds.size() == 0) {
            return R.fail("请选择校区");
        }
        for (Integer campusId : campusIds) {
            //判断是否存在已配置的校区
            List<User> userList = userService.getUserByCampusId(campusId);
            if (userList != null && userList.size() > 0) {
                return R.fail("已配置校区，不允许删除");
            }
        }
        campusService.removeByIds(campusIds);
        return R.ok();
    }

    /**
     * 分页查询(包括部门)校区
     *
     * @param curr
     * @param campusDTO
     * @return
     */
    @GetMapping("/campuss/pageTree/{curr}")
    public R<IPage<CampusDTO>> campusTreeInfoPage(@PathVariable("curr") int curr, @ModelAttribute("campusDTO") CampusDTO campusDTO) {
        Page<CampusDTO> page = new Page<CampusDTO>().setCurrent(curr);
        IPage<CampusDTO> sourcePage = campusService.getCampusDeptTreePage(page, campusDTO);
        return R.ok(sourcePage);
    }

    /**
     * 分页查询校区
     *
     * @param curr
     * @param campusDTO
     * @return
     */
    @GetMapping("/campuss/page/{curr}")
    public R<IPage<CampusDTO>> campusInfoPage(@PathVariable("curr") int curr, @ModelAttribute("campusDTO") CampusDTO campusDTO) {
        Page<CampusDTO> page = new Page<CampusDTO>().setCurrent(curr);
        IPage<CampusDTO> sourcePage = campusService.getCampusPage(page, campusDTO);
        return R.ok(sourcePage);
    }

    /**
     * 查询校区
     *
     * @return
     */
    @GetMapping("/campuss")
    public R campusInfoList() {
        List<Campus> campusList = campusService.getCampusList();
        return R.ok(campusList);
    }
}
