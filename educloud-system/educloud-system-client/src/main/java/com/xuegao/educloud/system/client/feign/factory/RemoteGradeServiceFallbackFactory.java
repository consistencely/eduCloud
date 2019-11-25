package com.xuegao.educloud.system.client.feign.factory;

import com.xuegao.educloud.system.client.feign.RemoteGradeService;
import com.xuegao.educloud.system.client.feign.RemoteSubjectService;
import com.xuegao.educloud.system.client.feign.fallback.RemoteGradeServiceFallback;
import com.xuegao.educloud.system.client.feign.fallback.RemoteSubjectServiceFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: LIM
 * @Date: 2019/11/19 14:12
 * @Description:
 */
@Component
public class RemoteGradeServiceFallbackFactory implements FallbackFactory<RemoteGradeService> {

    @Override
    public RemoteGradeService create(Throwable throwable) {
        RemoteGradeServiceFallback serviceFallback = new RemoteGradeServiceFallback();
        serviceFallback.setCause(throwable);
        return serviceFallback;
    }


}
