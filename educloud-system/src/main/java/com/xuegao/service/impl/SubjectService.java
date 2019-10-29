package com.xuegao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.dao.SubjectDao;
import com.xuegao.entities.Subject;
import com.xuegao.params.dto.SubjectGradeDTO;
import com.xuegao.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubjectService extends ServiceImpl<SubjectDao,Subject> implements ISubjectService {

    @Override
    public IPage<Subject> getSubjectPage(Page<Subject> page) {
        LambdaQueryWrapper<Subject> wrapper = Wrappers.<Subject>lambdaQuery()
                .orderByDesc(Subject::getSort)
                .orderByAsc(Subject::getCreateTime);
        return this.page(page,wrapper);
    }

    @Override
    @Transactional
    public void saveSubjectGrade(SubjectGradeDTO subjectGradeDTO) {
        Integer grade = subjectGradeDTO.getGradeId();
        //先删除后添加
        baseMapper.delSubjectGradeByGrade(grade);
        if(StringUtils.isNotBlank(subjectGradeDTO.getSubjectIds())){
            String[] idStrs = subjectGradeDTO.getSubjectIds().split(",");
            List<Integer> subjectIds = Arrays.stream(idStrs).map(Integer::valueOf).collect(Collectors.toList());
            baseMapper.saveSubjectGrade(grade,subjectIds);
        }
    }
}
