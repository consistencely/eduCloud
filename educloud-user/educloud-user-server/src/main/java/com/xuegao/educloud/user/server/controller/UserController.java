package com.xuegao.educloud.user.server.controller;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 13:53
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    /**
     * 新增用户
     * @param userInfoDTO
     * @return
     */
    @PostMapping("")
    public R saveUser(@RequestBody UserInfoDTO userInfoDTO){
        //参数校验 todo
        userService.saveUser(userInfoDTO);
        return R.ok();
    }

}
