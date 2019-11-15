package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.UserRole;
import com.xuegao.educloud.user.server.dao.UserRoleDao;
import com.xuegao.educloud.user.server.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Auther: ZWei
 * @Date: 2019/11/15 09:25
 * @Description:
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {
}
