package com.xuegao.educloud.common;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import org.junit.Test;
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
//        List<Integer> list = Arrays.asList(1,2,3);
        List<Integer> list = new ArrayList<>();
        System.out.println(IterUtil.isEmpty(list));

    }
}
