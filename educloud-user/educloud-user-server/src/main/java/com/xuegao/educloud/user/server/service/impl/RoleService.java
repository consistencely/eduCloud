package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;
import com.xuegao.educloud.user.server.dao.RoleDao;
import com.xuegao.educloud.user.server.service.IRoleService;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class RoleService extends ServiceImpl<RoleDao,Role> implements IRoleService {

    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Role> getRolePage(Page<Role> page) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().select(Role::getRoleId, Role::getRoleName, Role::getIsDefault, Role::getDescription);
        return this.page(page, wrapper);
    }

    @Override
    public List<Role> getRoleList() {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.<Role>lambdaQuery()
                .select(Role::getRoleId, Role::getRoleName);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Integer updateRole(RoleDTO roleDTO) {
        LambdaUpdateWrapper<Role> updateWrapper = Wrappers.<Role>lambdaUpdate()
                .eq(Role::getRoleId, roleDTO.getRoleId())
                .set(Role::getRoleName, roleDTO.getRoleName())
                .set(Role::getDescription, roleDTO.getDescription());
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public Integer saveRole(RoleDTO roleDTO) {
        Role role = new Role()
                .setRoleName(roleDTO.getRoleName()).setDescription(roleDTO.getDescription());
        return baseMapper.insert(role);
    }
}
