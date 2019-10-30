package com.xuegao.educloud.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.system.entities.Grade;
import com.xuegao.educloud.system.params.vo.GradeWithSubjectVO;

import java.util.List;

public interface IGradeService extends IService<Grade> {
    IPage<Grade> getGradePage(Page<Grade> page);

    List<GradeWithSubjectVO> getGradesWithSubject();

    /**
     * 所有学段
     * @return
     */
    List<Grade> getGrades();
}
