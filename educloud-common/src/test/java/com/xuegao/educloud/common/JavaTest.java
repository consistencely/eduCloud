package com.xuegao.educloud.common;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 14:54
 * @Description:
 */
public class JavaTest {

    @Test
    public void test(){
        String format = "XG_{}{}";
        String phone = "13160675318";
        String phoneSub = StrUtil.sub(phone,phone.length() - 4,phone.length());

        String str =  StrUtil.format(format,RandomUtil.randomString(8),phoneSub);
        System.out.println(str);


    }
}
