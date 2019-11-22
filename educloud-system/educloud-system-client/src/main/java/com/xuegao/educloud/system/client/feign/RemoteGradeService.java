package com.xuegao.educloud.system.client.feign;

import com.xuegao.educloud.common.constants.ServiceNameConstants;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.factory.RemoteGradeServiceFallbackFactory;
import com.xuegao.educloud.system.client.feign.factory.RemoteSubjectServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "remoteGradeService",name = ServiceNameConstants.SYS_SERVICE,fallbackFactory = RemoteGradeServiceFallbackFactory.class)
public interface RemoteGradeService {

    @GetMapping("/grades/ids")
    R<List<Grade>> getGradeByIds(@RequestParam("ids") Integer[] ids);

}
