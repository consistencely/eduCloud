package com.xuegao.educloud.user.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.entities.UserAddress;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:24
 * @Description:
 */
public interface IUserAddressService extends IService<UserAddress> {

    /**
     * 用户单个地址
     * @param userId
     * @return
     */
    UserAddress getOneByUserId(long userId);

    void saveOrUpdateAddr(UserAddress userAddress);
}
