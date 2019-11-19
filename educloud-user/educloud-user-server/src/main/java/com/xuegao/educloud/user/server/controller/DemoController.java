package com.xuegao.educloud.user.server.controller;

import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.common.response.Result;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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



    @GetMapping("/subject/demo2/{id}")
    public Subject demoSubejct(@PathVariable("id") int id){
        String json = systemClient.demo(id);

        log.info(json);
        return null;
    }

    @GetMapping("/subject/demo/{id}")
    public R demoSubject(@PathVariable("id") int id){
        R result = systemClient.getById(id);
        log.info("subject -> {}",result);
        return result;
    }

    @PostMapping("json")
    public R jsonTest(@RequestBody User info){
        info.setModifyTime(new Date());
        String str = JSONUtil.toJsonStr(info);
        log.info("JSON -> {}",str);
        return R.ok(info);

    }

}
