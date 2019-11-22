package com.xuegao.educloud.system.client.feign.fallback;

import com.xuegao.educloud.system.client.feign.RemoteSubjectService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: LIM
 * @Date: 2019/11/6 15:34
 * @Description:
 */
@Component
@Slf4j
public class RemoteSubjectServiceFallback implements RemoteSubjectService {

    @Setter
    private Throwable cause;

}
