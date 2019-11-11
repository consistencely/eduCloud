package com.xuegao.educloud.system.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.params.vo.GradeWithSubjectVO;
import com.xuegao.educloud.system.server.dao.GradeDao;
import com.xuegao.educloud.system.server.service.IGradeService;
import com.xuegao.educloud.system.server.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GradeService extends ServiceImpl<GradeDao,Grade> implements IGradeService {
    @Autowired
    private ISubjectService subjectService;

    @Override
    public IPage<Grade> getGradePage(Page<Grade> page) {
        LambdaQueryWrapper<Grade> wrapper = Wrappers.<Grade>lambdaQuery()
                .orderByAsc(Grade::getSort)
                .orderByAsc(Grade::getCreateTime);
        return this.page(page,wrapper);
    }

    @Override
    public List<Grade> getGrades() {
        LambdaQueryWrapper<Grade> wrapper = Wrappers.<Grade>lambdaQuery()
                .orderByAsc(Grade::getSort)
                .orderByAsc(Grade::getCreateTime);

        return this.list(wrapper);
    }

    @Override
    public List<GradeWithSubjectVO> getGradesWithSubject() {
        List<Grade> grades = this.getGrades();
        List<GradeWithSubjectVO> list = grades.stream().map(grade -> {
            GradeWithSubjectVO vo = new GradeWithSubjectVO(grade.getGradeId(), grade.getGradeName());
            List<Subject> subjects = subjectService.getSubjectsByGrade(grade.getGradeId());
            vo.setSubjects(subjects);
            return vo;
        }).collect(Collectors.toList());


        return list;
    }


}
