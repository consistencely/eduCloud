package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import com.xuegao.educloud.user.server.error.ECUserExceptionEnum;
import com.xuegao.educloud.user.server.service.ISourceService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zWei
 * @Date: 2019/11/13 13:56
 * @Description:
 */
@RestController
@RequestMapping("/sources")
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
    @PostMapping
    public boolean saveSource(@Valid @RequestBody SourceDTO sourceDTO) {
        return sourceService.saveSource(sourceDTO) > 0;
    }

    /**
     * 修改用户
     *
     * @param sourceDTO
     * @return
     */
    @PutMapping("/{sourceId}")
    public boolean updateSource(@PathVariable("sourceId") int sourceId,@Valid @RequestBody SourceDTO sourceDTO) {

        //参数校验
        Source sourceInfo = sourceService.getById(sourceId);
        if (sourceInfo == null) {
            throw new ServiceException(ECUserExceptionEnum.SOURCE_NOT_FOUND);
        }
        sourceDTO.setSourceId(sourceId);
        return sourceService.updateSource(sourceDTO) > 0;
    }


    /**
     * 生源信息
     *
     * @param sourceId
     * @return
     */
    @GetMapping("/{sourceId}")
    public Source getSourceInfo(@PathVariable("sourceId") Integer sourceId) {
        Source source = sourceService.getSourceInfo(sourceId);
        if (source == null) {
            throw new ServiceException(ECUserExceptionEnum.SOURCE_NOT_FOUND);
        }
        return sourceService.getSourceInfo(sourceId);
    }

    /**
     * 批量删除生源信息
     *
     * @param sourceMap 生源ID数组
     * @return
     */
    @DeleteMapping("/batch")
    public void batchDeleteSource(@RequestBody Map<String, List<Integer>> sourceMap) {
        List<Integer> sourceIds = sourceMap.get("sourceIds");
        if (sourceIds == null || sourceIds.size() == 0) {
            throw new InvalidRequestException("生源ID不能为空");
        }
        for (Integer sourceId : sourceIds) {
            //判断是否存在已配置的生源地
            List<User> userList = userService.getUserBySourceId(sourceId);
            if (userList != null && userList.size() > 0) {
                throw new ServiceException(ECUserExceptionEnum.SOURCE_NOTALLOW_DEL);
            }
        }
        sourceService.removeByIds(sourceIds);
    }

    /**
     * 分页查询生源
     * @param pageNum
     * @param pageSize
     * @param sourceDTO
     * @return
     */
    @GetMapping("/page")
    public IPage<SourceDTO> sourceInfoPage(@RequestParam(value = "pageNum",defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                              @ModelAttribute("sourceDTO") SourceDTO sourceDTO) {
        Page<SourceDTO> page = new Page<SourceDTO>().setCurrent(pageNum).setSize(pageSize);
        return sourceService.getSourcePage(page, sourceDTO);
    }

    /**
     * 查询生源地
     *
     * @return
     */
    @GetMapping
    public List<Source> sourceInfoList() {
        return sourceService.getSourceList();
    }

}
