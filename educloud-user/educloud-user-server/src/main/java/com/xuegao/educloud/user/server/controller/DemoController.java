package com.xuegao.educloud.user.server.controller;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.feign.SystemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String demo(){
        return "HELLO WORD";
    }

    @GetMapping("demo/subject")
    public R demoSubejct(){
        R subject = systemClient.getAllSubject();
        subject.setMsg("随便一点信息");
        return subject;
    }
}
