package com.xuegao.educloud.system.client.feign.fallback;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.SystemClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/6 15:34
 * @Description:
 */
@Component
@Slf4j
public class SystemClientFallBack implements SystemClient {

    @Override
    public R<List<Grade>> getGradeByIds(Integer[] ids) {
        return R.fail("hystrix -> 请求超时...");
    }

    @Override
    public R getAllSubject() {
        log.error("SystemClient.getAllSubject请求超时");
        return R.fail("hystrix -> 请求超时...");
    }
}
