package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;
import com.xuegao.educloud.user.server.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:27
 * @Description:
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询角色
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Role> getRoleById(@PathVariable("id") int id){
        Role role = roleService.getById(id);
        return R.ok(role);
    }

    /**
     * 分页查询角色
     * @param curr
     * @return
     */
    @GetMapping("/page/{curr}")
    public R<IPage<Role>> getByPage(@PathVariable("curr") int curr){
        Page<Role> page = new Page<Role>().setCurrent(curr);
        IPage<Role> roleRage = roleService.getRolePage(page);
        return R.ok(roleRage);
    }

    /**
     * 新增或删除角色
     * @return
     */
    @PostMapping("/{id}")
    public R saveOrUpdate(@RequestBody RoleDTO roleDTO){
        if(StrUtil.isEmpty(roleDTO.getName())){
            return R.fail("请输入角色名称");
        }
        //TODO
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        boolean success = roleService.saveOrUpdate(role);
        return success ? R.ok() : R.fail("保存失败");
    }

    /**
     * 删除角色
     * @return
     */
    @DeleteMapping("/{id}")
    public R delRole(@PathVariable("id") int id){
        boolean success = roleService.removeById(id);
        return success ? R.ok() : R.fail("删除失败");
    }
}
