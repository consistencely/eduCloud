package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.server.BaseTest;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Auther: LIM
 * @Date: 2019/11/6 09:30
 * @Description:
 */
public class RoleServiceTest extends BaseTest{

    @Autowired
    private IRoleService roleService;

    @Test
    public void testPage(){
        int curr = 1;
        Page<Role> page = new Page<Role>().setCurrent(curr);
        IPage<Role> roleRage = roleService.getRolePage(page);
        printOut(roleRage);
    }
}