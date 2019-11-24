package com.xuegao.educloud.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.exception.resource.ErrorResource;
import org.junit.Test;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 14:54
 * @Description:
 */
public class JavaTest {

    @Test
    public void test(){
        String str= "{code:1234,message:'什么错误'}";
        if(JSONUtil.isJson(str)){
            ErrorResource errorbean = JSONUtil.toBean(str, ErrorResource.class);
            if(errorbean.getCode() != null){

            }
        }

        System.out.println(JSONUtil.isJson(str));
    }
}
