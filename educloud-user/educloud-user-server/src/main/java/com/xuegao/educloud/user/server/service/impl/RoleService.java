package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.server.dao.RoleDao;
import com.xuegao.educloud.user.server.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class RoleService extends ServiceImpl<RoleDao,Role> implements IRoleService {

    /**
     * 分页查询角色
     * SELECT id,name,description FROM educloud_role WHERE is_del=0
     * @param page
     * @return
     */
    @Override
    public IPage<Role> getRolePage(Page<Role> page) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().select(Role::getId, Role::getName, Role::getDescription);
        return this.page(page,wrapper);
    }
}
