package com.xuegao.educloud.user.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.UserRole;
import com.xuegao.educloud.user.client.params.dto.RoleDTO;
import com.xuegao.educloud.user.client.params.vo.UserRoleVO;
import com.xuegao.educloud.user.client.error.ECUserExceptionEnum;
import com.xuegao.educloud.user.server.service.IRoleService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.xuegao.educloud.user.server.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:27
 * @Description:
 */
@RestController
@RequestMapping("/roles")
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
     *
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}")
    public Role getRoleById(@PathVariable("roleId") int roleId) {
        return roleService.getById(roleId);
    }

    /**
     * 分页查询角色
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public IPage<Role> getByPage(@RequestParam(value = "pageNum", defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize) {
        Page<Role> page = new Page<Role>().setCurrent(pageNum).setSize(pageSize);
        return roleService.getRolePage(page);
    }

    /**
     * 修改角色
     *
     * @return
     */
    @PutMapping("/{roleId}")
    public boolean saveOrUpdate(@PathVariable("roleId") int roleId, @Valid @RequestBody RoleDTO roleDTO) {
        Role roleInfo = roleService.getById(roleId);
        if (roleInfo == null) {
            throw new ServiceException(ECUserExceptionEnum.ROLE_NOT_FOUND);
        }
        roleDTO.setRoleId(roleId);
        return roleService.updateRole(roleDTO) > 0;
    }

    /**
     * 新增生源
     *
     * @return
     */
    @PostMapping
    public boolean saveSource(@Valid @RequestBody RoleDTO roleDTO) {
        return roleService.saveRole(roleDTO) > 0;
    }


    /**
     * 批量删除角色
     *
     * @return
     */
    @DeleteMapping("/batch")
    public void delRole(@RequestBody Map<String, List<Integer>> roleMap) {
        List<Integer> roleIds = roleMap.get("roleIds");
        if (roleIds == null || roleIds.size() == 0) {
            throw new InvalidRequestException("角色ID不能为空");
        }
        for (Integer roleId : roleIds) {
            //判断是否存在拥有角色的用户
            List<UserRole> userRoleList = userRoleService.getUserByRoleId(roleId);
            if (userRoleList != null && userRoleList.size() > 0) {
                throw new ServiceException(ECUserExceptionEnum.ROLE_NOTALLOW_DEL);

            }
        }
        roleService.removeByIds(roleIds);
    }

    /**
     * 角色对应用户数
     *
     * @return
     */
    @GetMapping("/usernum")
    public List<UserRoleVO> userNum() {
        return userService.getUserNumGroupRole();
    }

    /*
     * 查询角色
     *
     * @return
     */
    @GetMapping
    public List<Role> roleInfoList() {
        return roleService.getRoleList();
    }

}
