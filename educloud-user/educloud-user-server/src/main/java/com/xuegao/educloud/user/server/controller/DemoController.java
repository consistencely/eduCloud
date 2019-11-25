package com.xuegao.educloud.user.server.controller;

import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.user.client.entities.User;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("demo")
public class DemoController {


    @GetMapping("grade")
    public Grade getGrade() {
        log.info("返回类型 对象 ");
        return new Grade().setGradeId(1).setGradeName("高二");
    }


    @GetMapping("grade/{id}")
    public boolean isGrade(@PathVariable("id") int id) {
        log.info("返回类型 boolean ");
        return id == 0;
    }

    @GetMapping("/{id}")
    public int test(@PathVariable("id") int id) {
        log.info("返回类型 int ");
        if(id == 0){
            int a = 1 / 0;
        }
        return id;
    }

    @GetMapping("/void")
    public void v() {
        log.info("返回类型 void ");
    }

    @GetMapping("msg")
    public R demo(HttpServletRequest request, HttpServletResponse response) {
        return R.ok("错误信息");
    }

    @GetMapping("msg2/{curr}")
    public R dem2o(@PathVariable("curr") int curr, @ModelAttribute("name") String name) {
        return R.ok(name);
    }


    @PostMapping("json")
    public R jsonTest(@RequestBody User info) {
        info.setModifyTime(new Date());
        String str = JSONUtil.toJsonStr(info);
        log.info("JSON -> {}", str);
        return R.ok(info);

    }

}
