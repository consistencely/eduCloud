package com.xuegao.educloud.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao extends BaseMapper<User> {

    Integer saveUserRole(@Param("userId") Long userId,@Param("roleIds") int[] roleIds);

    Integer saveUserGrade(@Param("userId") Long userId,@Param("gradeId") int[] gradeIds);
}
