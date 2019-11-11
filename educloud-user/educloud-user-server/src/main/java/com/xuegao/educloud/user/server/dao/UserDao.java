package com.xuegao.educloud.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    Integer saveUserRole(@Param("userId") Long userId,@Param("roleIds") Integer[] roleIds);

    Integer saveUserGrade(@Param("userId") Long userId,@Param("gradeIds") Integer[] gradeIds);

    Integer clearUserRole(Long userId);

    Integer clearUserGrade(Long userId);

    List<Integer> getUserRoles(long userId);

    List<Integer> getUserGrades(long userId);

    Integer batchClearUserGrade(@Param("userIds") Long[] userIds);

    Integer batchUpdateOrgan(@Param("organizationId") int organizationId, @Param("userIds") Long[] userIds);

    IPage<UserVO> getUserPage(Page<UserVO> page, @Param("userQuery") UserQuery userQuery);
}
