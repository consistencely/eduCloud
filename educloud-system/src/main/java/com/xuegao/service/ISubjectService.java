package com.xuegao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.entities.Subject;
import com.xuegao.params.dto.SubjectGradeDTO;

import java.util.List;

public interface ISubjectService extends IService<Subject> {
    IPage<Subject> getSubjectPage(Page<Subject> page);

    void saveSubjectGrade(SubjectGradeDTO subjectGradeDTO);
}
