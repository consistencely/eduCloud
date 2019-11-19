package com.xuegao.educloud.system.client.feign.factory;

import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.system.client.feign.fallback.SystemClientFallBack;
import feign.hystrix.FallbackFactory;

/**
 * @Auther: LIM
 * @Date: 2019/11/19 14:12
 * @Description:
 */
public class SystemClientFallBackFactory implements FallbackFactory<SystemClient> {
    @Override
    public SystemClient create(Throwable throwable) {
        SystemClientFallBack systemClientFallBack = new SystemClientFallBack();
        systemClientFallBack.setCause(throwable);
        return systemClientFallBack;
    }
}
