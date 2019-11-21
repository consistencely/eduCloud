package com.xuegao.educloud.system.client.feign;

import com.xuegao.educloud.common.constants.ServiceNameConstants;
import com.xuegao.educloud.system.client.feign.factory.RemoteSubjectServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = ServiceNameConstants.SYS_SERVICE,fallbackFactory = RemoteSubjectServiceFallbackFactory.class)
public interface RemoteSubjectService {


}
