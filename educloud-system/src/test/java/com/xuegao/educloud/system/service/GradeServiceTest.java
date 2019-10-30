package com.xuegao.educloud.system.service;

import com.xuegao.educloud.system.BaseTest;
import com.xuegao.educloud.system.params.vo.GradeWithSubjectVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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
    public void test(){
        log.debug("********debug***********");
        log.info("********info***********");
        log.warn("********warn***********");
        log.error("********error***********");
    }

}