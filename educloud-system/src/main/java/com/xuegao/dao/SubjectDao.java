package com.xuegao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.entities.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectDao extends BaseMapper<Subject> {
    Integer delSubjectGradeByGrade(Integer gradeId);

    void saveSubjectGrade(@Param("grade") Integer grade, @Param("subjectIds") List<Integer> subjectIds);
}
