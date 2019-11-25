package com.xuegao.educloud.common;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.exception.enums.CommonExceptionEnum;
import com.xuegao.educloud.common.exception.resource.ErrorResource;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 14:54
 * @Description:
 */
public class JavaTest {

    @Test
    public void test(){
        int code = CommonExceptionEnum.INVALID_PARAM.getCode();
        String msg = "参数异常";

        String message = "{code:1234,messagae:什么错误}";

        if(StrUtil.isNotEmpty(message)){
            if(JSONUtil.isJson(message)){
                ErrorResource errorbean = JSONUtil.toBean(message, ErrorResource.class);
                if(errorbean.getCode() != null){
                    code = errorbean.getCode();
                }
                if(StrUtil.isNotEmpty(errorbean.getMessage())){
                    msg = errorbean.getMessage();
                }

            }else{
                msg = message;
            }
        }

        System.out.println(code + "->" + msg);


    }
}
