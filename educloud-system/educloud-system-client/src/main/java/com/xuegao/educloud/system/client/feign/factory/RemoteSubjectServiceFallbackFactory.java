package com.xuegao.educloud.system.client.feign.factory;

import com.xuegao.educloud.system.client.feign.RemoteSubjectService;
import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.system.client.feign.fallback.RemoteSubjectServiceFallback;
import com.xuegao.educloud.system.client.feign.fallback.SystemClientFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: LIM
 * @Date: 2019/11/19 14:12
 * @Description:
 */
@Component
public class RemoteSubjectServiceFallbackFactory implements FallbackFactory<RemoteSubjectService> {

    @Override
    public RemoteSubjectService create(Throwable throwable) {
        RemoteSubjectServiceFallback subjectServiceFallback = new RemoteSubjectServiceFallback();
        subjectServiceFallback.setCause(throwable);
        return subjectServiceFallback;
    }


}
