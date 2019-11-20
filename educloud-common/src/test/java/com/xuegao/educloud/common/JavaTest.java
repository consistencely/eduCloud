package com.xuegao.educloud.common;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xuegao.educloud.common.exception.ErrorResource;
import com.xuegao.educloud.common.response.Result;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 14:54
 * @Description:
 */
public class JavaTest {

    @Test
    public void test(){
//        Result<Object> result = Result.success();
//        System.out.println(result instanceof Result);
//        System.out.println(result instanceof ResponseEntity);
//        System.out.println(result.getClass() );
//        ResponseEntity<Object> responseEntity = new ResponseEntity<>(null, null, HttpStatus.OK);
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().build();
        Result<Object> result = (Result<Object>) responseEntity;
        System.out.println(result);
    }
}
