package com.xuegao.educloud.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserRoleVO;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    /**
     * 保存用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    Integer saveUserRole(@Param("userId") Long userId,@Param("roleIds") Integer[] roleIds);

    /**
     * 保存用户年级
     * @param userId
     * @param gradeIds
     * @return
     */
    Integer saveUserGrade(@Param("userId") Long userId,@Param("gradeIds") Integer[] gradeIds);

    /**
     * 清除用户角色
     * @param userId
     * @return
     */
    Integer clearUserRole(Long userId);

    /**
     * 清除用户年级
     * @param userId
     * @return
     */
    Integer clearUserGrade(Long userId);

    List<Integer> getUserRoles(long userId);

    List<Integer> getUserGrades(long userId);

    Integer batchClearUserGrade(@Param("userIds") Long[] userIds);

    Integer batchUpdateOrgan(@Param("campusId") int campusId, @Param("userIds") Long[] userIds);

    /**
     * 分页查询用户信息
     * @param page
     * @param userQuery
     * @return
     */
    IPage<UserVO> getUserPage(Page<UserVO> page, @Param("userQuery") UserQuery userQuery);

    List<UserVO> getUserGradesRoles(@Param("userId") List<Long> userIds);

    /**
     * 角色对应用户数
     * @return
     */
    List<UserRoleVO> getUserNumGroupRole();

    /**
     * 批量修改用户状态
     * @param status
     * @param userIds
     * @return
     */
    Integer batchUpdateStatus(@Param("status") byte status,@Param("userIds") List<Long> userIds);
}
