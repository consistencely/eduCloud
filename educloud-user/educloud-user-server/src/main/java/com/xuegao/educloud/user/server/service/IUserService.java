package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:24
 * @Description:
 */
public interface IUserService extends IService<User> {

    /**
     * 新增用户
     * @param userInfoDTO
     */
    void saveUser(UserInfoDTO userInfoDTO);

    /**
     * 修改用户
     * @param userInfoDTO
     */
    void updateUser(UserInfoDTO userInfoDTO);

    /**
     * 手机号是否存在
     * @param phone
     * @return
     */
    boolean isPhoneExist(String phone);

    /**
     * 手机号查用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 用户所属角色
     * @param userId
     * @return
     */
    List<Integer> getUserRoles(long userId);

    /**
     * 用户所属年级
     * @param userId
     * @return
     */
    List<Integer> getUserGrades(long userId);

    /**
     * 用户信息
     * @param userId
     * @return
     */
    UserInfoDTO getUserInfo(long userId);

    /**
     * 批量修改用户状态
     * @param statusCode
     * @param userIds
     */
    void batchUpdateStatus(byte statusCode, List<Long> userIds);

    /**
     * 批量修改用户
     * @param userInfo
     */
    void batchUpdate(UserInfoDTO userInfo);

    /**
     * 分页查询用户信息
     * @param page
     * @param userQuery
     * @return
     */
    IPage<UserVO> getUserPage(Page<UserVO> page, UserQuery userQuery);

}
