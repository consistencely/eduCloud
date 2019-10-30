package com.xuegao.educloud.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.system.entities.Grade;
import com.xuegao.educloud.system.entities.Kpoint;
import com.xuegao.educloud.system.params.vo.GradeWithSubjectVO;

import java.util.List;

public interface IKpointService extends IService<Kpoint> {

    /**
     * 学段学科下的考点
     * @param gradeId
     * @param subjectId
     * @return
     */
    List<Kpoint> getKpointByGradeSubject(int gradeId, int subjectId);
}
