package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.server.dao.CampusDao;
import com.xuegao.educloud.user.server.dao.RoleDao;
import com.xuegao.educloud.user.server.service.ICampusService;
import com.xuegao.educloud.user.server.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class CampusService extends ServiceImpl<CampusDao,Campus> implements ICampusService {

}
