package com.xuegao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.BaseTest;
import com.xuegao.entities.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:27
 * @Description:
 */

public class SubjectServiceTest extends BaseTest {

    @Autowired
    private ISubjectService subjectService;

    @Test
    public void test(){
        Page<Subject> page = new Page<Subject>().setCurrent(1);
        IPage<Subject> subjectPage = subjectService.getSubjectPage(page);
        printOut(subjectPage);
    }


}