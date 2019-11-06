package com.xuegao.educloud.user.server.controller;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.user.client.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: LIM
 * @Date: 2019/11/1 16:10
 * @Description:
 */
@RestController
public class DemoController {

    @Autowired
    private SystemClient systemClient;


    @GetMapping("msg")
    public R demo(HttpServletRequest request, HttpServletResponse response){
        return R.ok("错误信息");
    }

    @GetMapping("msg2")
    public R dem2o(){
        Role role = new Role().setId(1).setName("删除权限");
        return R.ok(role);
    }

    @GetMapping("demo/subject")
    public R demoSubejct(){
        R subject = systemClient.getAllSubject();
        return subject;
    }

}
