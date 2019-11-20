package com.xuegao.educloud.system.client.feign.fallback;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.common.response.Result;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.feign.SystemClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Setter
    private Throwable cause;

    @Override
    public R<List<Grade>> getGradeByIds(Integer[] ids) {
        return R.fail("hystrix -> 请求超时...");
    }

    @Override
    public R getAllSubject() {
        log.error("SystemClient.getAllSubject请求超时");
        return R.fail("hystrix -> 请求超时...");
    }

    @Override
    public ResponseEntity<Subject> demo(int id) {
        log.error("熔断器12：开 -> {}",cause.getMessage());
        return ResponseEntity.ok(new Subject());
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        log.error("熔断器12：开 -> {}",cause.getMessage());
        return Result.success();
    }
}
