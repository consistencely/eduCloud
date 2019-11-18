package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import com.xuegao.educloud.user.server.service.ISourceService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zWei
 * @Date: 2019/11/13 13:56
 * @Description:
 */
@RestController
@RequestMapping
@Slf4j
public class SourceController {


    @Autowired
    private ISourceService sourceService;
    @Autowired
    private IUserService userService;

    /**
     * 新增生源
     *
     * @param sourceDTO
     * @return
     */
    @PostMapping("/source")
    public R saveSource(@RequestBody SourceDTO sourceDTO) {
        if (StrUtil.isEmpty(sourceDTO.getSourceName())) {
            return R.fail("生源地不能为空");
        }
        if (StrUtil.isEmpty(sourceDTO.getApplyWay())) {
            return R.fail("报名方式不能为空");
        }
        Integer success = sourceService.saveSource(sourceDTO);
        if (success > 0) {
            return R.ok();
        } else {
            return R.fail("保存失败");
        }
    }

    /**
     * 修改用户
     *
     * @param sourceDTO
     * @return
     */
    @PutMapping("/source")
    public R updateSource(@RequestBody SourceDTO sourceDTO) {

        //参数校验
        Integer sourceId = sourceDTO.getSourceId();
        if (sourceId == null) {
            return R.fail("生源ID为空");
        }
        Source sourceInfo = sourceService.getById(sourceId);
        if (sourceInfo == null) {
            return R.fail("生源信息不存在");
        }
        if (StrUtil.isEmpty(sourceDTO.getSourceName())) {
            return R.fail("生源地不能为空");
        }
        if (StrUtil.isEmpty(sourceDTO.getApplyWay())) {
            return R.fail("报名方式不能为空");
        }
        Integer count = sourceService.updateSource(sourceDTO);
        if (count > 0) {
            return R.ok();
        } else {
            return R.fail("修改失败");
        }
    }


    /**
     * 生源信息
     *
     * @param sourceId
     * @return
     */
    @GetMapping("/source/{sourceId}")
    public R<Source> getSourceInfo(@PathVariable("sourceId") Integer sourceId) {
        Source source = sourceService.getSourceInfo(sourceId);
        if (source == null) {
            return R.fail("生源信息不存在");
        }
        return R.ok(source);
    }

    /**
     * 批量删除生源信息
     *
     * @param sourceMap 生源ID数组
     * @return
     */
    @DeleteMapping("/sources")
    public R batchDeleteSource(@RequestBody Map<String, List<Integer>> sourceMap) {
        List<Integer> sourceIds = sourceMap.get("sourceIds");
        if (sourceIds == null || sourceIds.size() == 0) {
            return R.fail("请选择生源");
        }
        for (Integer sourceId : sourceIds) {
            //判断是否存在已配置的生源地
            List<User> userList = userService.getUserBySourceId(sourceId);
            if (userList != null && userList.size() > 0) {
                return R.fail("已配置生源地，不允许删除");
            }
        }
        sourceService.removeByIds(sourceIds);
        return R.ok();
    }

    /**
     * 分页查询生源
     *
     * @param curr
     * @param sourceDTO
     * @return
     */
    @GetMapping("/sources/page/{curr}")
    public R<IPage<SourceDTO>> sourceInfoPage(@PathVariable("curr") int curr, @ModelAttribute("sourceDTO") SourceDTO sourceDTO) {
        Page<SourceDTO> page = new Page<SourceDTO>().setCurrent(curr);
        IPage<SourceDTO> sourcePage = sourceService.getSourcePage(page, sourceDTO);
        return R.ok(sourcePage);
    }

    /**
     * 查询生源地
     *
     * @return
     */
    @GetMapping("/sources")
    public R sourceInfoList() {
        List<Source> sourceList = sourceService.getSourceList();
        return R.ok(sourceList);
    }

}
