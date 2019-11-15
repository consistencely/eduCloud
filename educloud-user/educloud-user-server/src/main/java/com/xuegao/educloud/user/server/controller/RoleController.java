package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;
import com.xuegao.educloud.user.client.params.vo.UserRoleVO;
import com.xuegao.educloud.user.server.service.IRoleService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private IUserService userService;

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
     * 新增或修改角色
     * @return
     */
    @PostMapping("/{id}")
    public R saveOrUpdate(@RequestBody RoleDTO roleDTO){
        if(StrUtil.isEmpty(roleDTO.getRoleName())){
            return R.fail("请输入角色名称");
        }
        Role role = new Role()
                .setRoleId(roleDTO.getRoleId())
                .setRoleName(roleDTO.getRoleName())
                .setDescription(roleDTO.getDescription());

        boolean success = roleService.saveOrUpdate(role);
        return success ? R.ok() : R.fail("保存失败");
    }

    /**
     * 批量删除角色
     * @return
     */
    @DeleteMapping("")
    public R delRole(@RequestBody int[] ids){
        boolean success = roleService.removeByIds(Arrays.asList(ids));
        return success ? R.ok() : R.fail("删除失败");
    }

    /**
     * 角色对应用户数
     * @return
     */
    @GetMapping("/roles/usernum")
    public R userNum(){
        List<UserRoleVO> userNums = userService.getUserNumGroupRole();
        return R.ok(userNums);
    }
}
