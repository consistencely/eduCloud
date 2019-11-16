package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:24
 * @Description:
 */
public interface IRoleService extends IService<Role> {

    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    IPage<Role> getRolePage(Page<Role> page);

    /**
     * 查询角色
     *
     * @return
     */
    List<Role> getRoleList();

    /**
     * 修改角色
     *
     * @param roleDTO
     * @return
     */
    Integer updateRole(RoleDTO roleDTO);

    /**
     * 保存角色
     * @param roleDTO
     * @return
     */
    Integer saveRole(RoleDTO roleDTO);
}
