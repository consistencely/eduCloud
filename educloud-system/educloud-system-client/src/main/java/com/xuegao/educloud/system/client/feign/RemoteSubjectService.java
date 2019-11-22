package com.xuegao.educloud.system.client.feign;

import com.xuegao.educloud.common.constants.ServiceNameConstants;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.feign.factory.RemoteSubjectServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ServiceNameConstants.SYS_SERVICE,fallbackFactory = RemoteSubjectServiceFallbackFactory.class)
public interface RemoteSubjectService {


}
