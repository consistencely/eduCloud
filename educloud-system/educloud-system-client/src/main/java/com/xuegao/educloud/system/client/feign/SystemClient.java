package com.xuegao.educloud.system.client.feign;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.fallback.SystemClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/1 16:15
 * @Description:
 */
@FeignClient(name = "system",fallback = SystemClientFallBack.class)
public interface SystemClient {

    @GetMapping("/grades/ids")
    R<List<Grade>> getGradeByIds(@RequestParam("ids") Integer[] ids);


    @GetMapping("/subject/all")
    R getAllSubject();
}
