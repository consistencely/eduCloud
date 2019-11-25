package com.xuegao.educloud.system.client.feign.fallback;

import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.RemoteGradeService;
import com.xuegao.educloud.system.client.feign.RemoteSubjectService;
import lombok.Setter;
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
public class RemoteGradeServiceFallback implements RemoteGradeService {

    @Setter
    private Throwable cause;

    @Override
    public R<List<Grade>> getGradeByIds(Integer[] ids) {
        return R.fail("hystrix -> 请求超时...");
    }
}
