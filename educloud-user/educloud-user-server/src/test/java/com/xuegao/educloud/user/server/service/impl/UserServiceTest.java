package com.xuegao.educloud.user.server.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.user.client.entities.UserAddress;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import com.xuegao.educloud.user.server.BaseTest;
import com.xuegao.educloud.user.server.service.IUserAddressService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 16:53
 * @Description:
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserAddressService userAddressService;

    @Test
    public void saveUser() throws Exception {
        UserInfoDTO dto = new UserInfoDTO()
                .setAvatar("http://www.baidu.com")
                .setPhone("13112345678")
                .setPassword("123456")
                .setRoleIds(new Integer[]{101})
                .setGradeIds(new Integer[]{44,55})
                .setSourceId(6)
                .setCampusId(101)
//                .setProvince("广东省")
//                .setCity("广州市")
//                .setCounty("天河区")
//                .setAddrDetail("穗丰大厦A座803")
//                .setBirthday(new Date())
//                .setTel("8008208820")
//                .setSchool("家里蹲大学")
                ;
        userService.saveUser(dto);
    }

    @Test
    public void getAddress() throws Exception {
        long userId = 1L;
        LambdaQueryWrapper<UserAddress> wrapper = Wrappers.<UserAddress>lambdaQuery().eq(UserAddress::getUserId, userId).last("LIMIT 1");
        UserAddress address = userAddressService.getOne(wrapper);
        printOut(address);

    }

    @Test
    public void getUser() throws Exception {
        UserInfoDTO userInfo = userService.getUserInfo(1L);
        printOut(userInfo);

    }

    @Test
    public void batch() throws Exception {
        List<Long> userIds = Arrays.asList(1L,2L);
//        userService.batchUpdateStatus((byte)1,userIds);


        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserIds(ArrayUtil.toArray(userIds,Long.class));
        userInfoDTO.setCampusId(88);
        userInfoDTO.setGradeIds(new Integer[]{101,105});
        userService.batchUpdate(userInfoDTO);
    }

    @Test
    public void testPage() {
        int[] arr = new int[]{1,2};
        int curr = 1;
        UserQuery userQuery = new UserQuery()
                .setNickname("567")
                .setPhone("131")
//                .setGradeIds(arr)
//                .setOrganIds(arr)
//                .setRoleIds(arr)
                .setStatusCodes(arr)
                .setRegisterSort(1)
                ;
        Page<UserVO> page = new Page<UserVO>().setCurrent(curr);
        IPage<UserVO> userPage = userService.getUserPage(page,userQuery);
        printOut(userPage);

    }
}