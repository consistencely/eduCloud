package com.xuegao.educloud.user.server.controller;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.feign.SystemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        response.setStatus(6006);
        return R.fail("错误信息");
    }


    @GetMapping("demo/subject")
    public R demoSubejct(){
        R subject = systemClient.getAllSubject();
        subject.setMsg("随便一点信息");
        return subject;
    }

}
