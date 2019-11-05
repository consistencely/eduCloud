package com.xuegao.educloud.user.server.service.impl;

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
}
