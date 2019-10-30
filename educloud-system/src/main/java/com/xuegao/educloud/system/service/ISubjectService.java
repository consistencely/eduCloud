package com.xuegao.educloud.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.system.entities.Subject;
import com.xuegao.educloud.system.params.dto.SubjectGradeDTO;

import java.util.List;

public interface ISubjectService extends IService<Subject> {
    IPage<Subject> getSubjectPage(Page<Subject> page);

    void saveSubjectGrade(SubjectGradeDTO subjectGradeDTO);

    /**
     * 学段所有学科
     * @param gradeId 学段ID
     * @return
     */
    List<Subject> getSubjectsByGrade(Integer gradeId);
}
