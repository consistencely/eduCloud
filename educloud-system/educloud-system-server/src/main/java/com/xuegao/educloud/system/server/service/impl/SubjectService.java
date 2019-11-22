package com.xuegao.educloud.system.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.params.dto.SubjectGradeDTO;
import com.xuegao.educloud.system.server.dao.SubjectDao;
import com.xuegao.educloud.system.server.service.ISubjectService;
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
                .orderByAsc(Subject::getSort)
                .orderByAsc(Subject::getCreateTime);
        return this.page(page,wrapper);
    }

    //TODO 事务
    @Override
    public boolean saveSubjectGrade(SubjectGradeDTO subjectGradeDTO) {
        Integer grade = subjectGradeDTO.getGradeId();
        if (grade == null) {
           return false;
        }

        //先删除后添加
        baseMapper.delSubjectGradeByGrade(grade);
        if(StringUtils.isNotBlank(subjectGradeDTO.getSubjectIds())){
            String[] idStrs = subjectGradeDTO.getSubjectIds().split(",");
            List<Integer> subjectIds = Arrays.stream(idStrs).map(Integer::valueOf).collect(Collectors.toList());
            baseMapper.saveSubjectGrade(grade,subjectIds);
        }
        return true;
    }

    /**
     * 学段所有学科
     *
     * @param gradeId 学段ID
     * @return
     */
    @Override
    public List<Subject> getSubjectsByGrade(Integer gradeId) {
        return baseMapper.getSubjectsByGrade(gradeId);
    }

    /**
     * 所有学科
     *
     * @return
     */
    @Override
    public List<Subject> getAllSubject() {
        LambdaQueryWrapper<Subject> wrapper = Wrappers.<Subject>lambdaQuery().orderByAsc(Subject::getSort);
        return this.list(wrapper);
    }
}
