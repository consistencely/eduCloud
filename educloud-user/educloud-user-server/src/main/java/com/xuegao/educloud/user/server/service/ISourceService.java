package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;

import java.util.List;

/**
 * @Auther: zWei
 * @Date: 2019/11/13 14:42
 * @Description:
 */
public interface ISourceService extends IService<Source> {

    /**
     * 分页查询生源信息
     *
     * @param page
     * @param sourceDTO
     * @return
     */
    IPage<SourceDTO> getSourcePage(Page<SourceDTO> page, SourceDTO sourceDTO);

    /**
     * 查询生源地
     *
     * @return
     */
    List<Source> getSourceList();

    /**
     * 修改生源信息
     *
     * @param sourceDTO
     * @return
     */
    Integer updateSource(SourceDTO sourceDTO);

    /**
     * 查询单个生源信息
     * @param sourceId
     * @return
     */
    Source getSourceInfo(Integer sourceId);

    /**
     * 保存生源地
     * @param sourceDTO
     */
    Integer saveSource(SourceDTO sourceDTO);
}
