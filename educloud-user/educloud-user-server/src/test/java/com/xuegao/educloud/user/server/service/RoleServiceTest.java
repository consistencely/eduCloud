package com.xuegao.educloud.user.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.server.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: LIM
 * @Date: 2019/11/6 09:30
 * @Description:
 */
public class RoleServiceTest extends BaseTest{

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICampusDepartmentService campusDepartmentService;

    @Test
    public void testPage(){
        int curr = 1;
        Page<Role> page = new Page<Role>().setCurrent(curr);
        IPage<Role> roleRage = roleService.getRolePage(page);
        printOut(roleRage);
    }

    @Test
    public void testBeanUtil(){
        CampusDepartment byId = campusDepartmentService.getById(1);
        CampusDeptDTO departmentDTO = new CampusDeptDTO();
        BeanUtil.copyProperties(byId,departmentDTO);
        printOut(departmentDTO);
    }

}