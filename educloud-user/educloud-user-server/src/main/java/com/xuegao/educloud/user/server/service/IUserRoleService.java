package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.UserRole;

import java.util.List;


/**
 * @Auther: ZWei
 * @Date: 2019/11/15 9:19
 * @Description:
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 查询用户是否存在角色
     * @param roleId
     * @return
     */
    List<UserRole> getUserByRoleId(Integer roleId);
}
