package com.xuegao.educloud.system.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.params.vo.GradeWithSubjectVO;
import com.xuegao.educloud.system.server.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/10/30 11:12
 * @Description:
 */
@Slf4j
public class GradeServiceTest extends BaseTest{

    @Autowired
    private IGradeService gradeService;
    @Test
    public void getGradesWithSubject() throws Exception {
        List<GradeWithSubjectVO> list = gradeService.getGradesWithSubject();
        printOut(list);
    }

    @Test
    public void page() throws Exception {
        Page<Grade> page = new Page<Grade>();
        IPage<Grade> gradePage = gradeService.getGradePage(page);
        printOut(gradePage);

    }

    @Test
    public void test(){
        log.debug("********debug***********");
        log.info("********info***********");
        log.warn("********warn***********");
        log.error("********error***********");
    }

}