package com.xuegao.educloud.system.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuegao.educloud.system.client.entities.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectDao extends BaseMapper<Subject> {
    Integer delSubjectGradeByGrade(Integer gradeId);

    void saveSubjectGrade(@Param("grade") Integer grade, @Param("subjectIds") List<Integer> subjectIds);

    /**
     * 获取学段所有学科
     * @param gradeId
     * @return
     */
    List<Subject> getSubjectsByGrade(Integer gradeId);
}
