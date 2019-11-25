package com.xuegao.educloud.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.params.dto.CampusDTO;
import org.apache.ibatis.annotations.Param;

public interface CampusDao extends BaseMapper<Campus> {

    IPage<CampusDTO> getCampusPage(Page<CampusDTO> page, @Param("campusDTO") CampusDTO campusDTO);
}
