package com.xuegao.educloud.system.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.educloud.system.client.entities.Kpoint;

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
