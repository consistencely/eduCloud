package com.xuegao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: LIM
 * @Date: 2019/9/24 16:29
 * @Description:
 */
@RestController
public class DemoController {


    @GetMapping("msg")
    public String demo(){
        return "HELLO WORD";
    }
}
