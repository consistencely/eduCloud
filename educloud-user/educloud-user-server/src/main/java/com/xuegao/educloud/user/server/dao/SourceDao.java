package com.xuegao.educloud.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import org.apache.ibatis.annotations.Param;

public interface SourceDao extends BaseMapper<Source> {

    IPage<SourceDTO> getSourcePage(Page<SourceDTO> page, @Param("source") Source source);
}
