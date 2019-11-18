package com.xuegao.educloud.user.server.controller;

import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/1 16:10
 * @Description:
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private SystemClient systemClient;


    @GetMapping("msg")
    public R demo(HttpServletRequest request, HttpServletResponse response){
        return R.ok("错误信息");
    }

    @GetMapping("msg2/{curr}")
    public R dem2o(@PathVariable("curr") int curr,@ModelAttribute("name") String name){
        return R.ok(name);
    }



    @GetMapping("demo/subject")
    public R demoSubejct(){
        R subject = systemClient.getAllSubject();
        return subject;
    }

    @PostMapping("json")
    public R jsonTest(@RequestBody User info){
        info.setModifyTime(new Date());
        String str = JSONUtil.toJsonStr(info);
        log.info("JSON -> {}",str);
        return R.ok(info);

    }

}
