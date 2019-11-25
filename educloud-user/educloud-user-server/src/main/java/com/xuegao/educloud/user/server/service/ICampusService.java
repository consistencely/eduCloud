package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.CampusDTO;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:24
 * @Description:
 */
public interface ICampusService extends IService<Campus> {

    /**
     * 保存校区
     *
     * @param campusDTO
     * @return
     */
    Integer saveCampus(CampusDTO campusDTO);

    /**
     * 修改校区
     *
     * @param campusDTO
     * @return
     */
    Integer updateCampus(CampusDTO campusDTO);

    /**
     * 分页查询校区(包括部门信息)
     *
     * @param page
     * @param campusDTO
     * @return
     */
    IPage<CampusDTO> getCampusDeptTreePage(Page<CampusDTO> page, CampusDTO campusDTO);

    /**
     * 查询校区信息
     *
     * @return
     */
    List<Campus> getCampusList();

    /**
     * 分页查询校区
     *
     * @param page
     * @param campusDTO
     * @return
     */
    IPage<CampusDTO> getCampusPage(Page<CampusDTO> page, CampusDTO campusDTO);
}
