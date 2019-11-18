package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.entities.UserRole;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;
import com.xuegao.educloud.user.client.params.vo.UserRoleVO;
import com.xuegao.educloud.user.server.service.IRoleService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import com.xuegao.educloud.user.server.service.IUserRoleService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:27
 * @Description:
 */
@RestController
@RequestMapping
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
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
     *
     * @param curr
     * @return
     */
    @GetMapping("/roles/page/{curr}")
    public R<IPage<Role>> getByPage(@PathVariable("curr") int curr) {
        Page<Role> page = new Page<Role>().setCurrent(curr);
        IPage<Role> roleRage = roleService.getRolePage(page);
        return R.ok(roleRage);
    }

    /**
     * 修改角色
     *
     * @return
     */
    @PutMapping("/role")
    public R saveOrUpdate(@RequestBody RoleDTO roleDTO) {
        Integer roleId = roleDTO.getRoleId();
        if (roleId == null) {
            return R.fail("角色ID为空");
        }
        Role roleInfo = roleService.getById(roleId);
        if (roleInfo == null) {
            return R.fail("该角色不存在");
        }
        if (StrUtil.isEmpty(roleDTO.getRoleName())) {
            return R.fail("请输入角色名称");
        }
        Integer count = roleService.updateRole(roleDTO);
        if (count > 0) {
            return R.ok();
        } else {
            return R.fail("修改失败");
        }
    }

    /**
     * 新增生源
     *
     * @param role
     * @return
     */
    @PostMapping("/role")
    public R saveSource(@RequestBody RoleDTO roleDTO) {
        if (StrUtil.isEmpty(roleDTO.getRoleName())) {
            return R.fail("角色名称不能为空");
        }
        Integer success = roleService.saveRole(roleDTO);
        if(success > 0){
            return R.ok();
        }else {
            return  R.fail("保存失败");
        }
    }


    /**
     * 批量删除角色
     *
     * @return
     */
    @DeleteMapping("/roles")
    public R delRole(@RequestBody Map<String, List<Integer>> roleMap) {
        List<Integer> roleIds = roleMap.get("roleIds");
        if (roleIds == null || roleIds.size() == 0) {
            return R.fail("请选择生源");
        }
        for (Integer roleId : roleIds) {
            //判断是否存在拥有角色的用户
            List<UserRole> userRoleList = userRoleService.getUserByRoleId(roleId);
            if (userRoleList != null && userRoleList.size() > 0) {
                return R.fail("用户已存在该角色，不允许删除");
            }
        }
        roleService.removeByIds(roleIds);
        return R.ok();
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

    /*
     * 查询角色
     *
     * @return
     */
    @GetMapping("/roles")
    public R roleInfoList() {
        List<Role> roleList = roleService.getRoleList();
        return R.ok(roleList);
    }

}
