package com.xuegao.educloud.user.server.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.entities.UserAddress;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.server.constants.UserConstants;
import com.xuegao.educloud.user.server.dao.UserAddressDao;
import com.xuegao.educloud.user.server.dao.UserDao;
import com.xuegao.educloud.user.server.service.IUserAddressService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class UserAddressService extends ServiceImpl<UserAddressDao, UserAddress> implements IUserAddressService{


    /**
     * 用户单个地址
     * SELECT address_id,user_id,province,city,county,addr_detail,tel,modify_time,create_time FROM educloud_user_address WHERE is_del=0 AND user_id = ? LIMIT 1
     * @param userId
     * @return
     */
    @Override
    public UserAddress getOneByUserId(long userId) {
        LambdaQueryWrapper<UserAddress> wrapper = Wrappers.<UserAddress>lambdaQuery().eq(UserAddress::getUserId, userId).last("LIMIT 1");
        return getOne(wrapper);
    }
}
