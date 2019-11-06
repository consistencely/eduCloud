package com.xuegao.educloud.system.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.system.client.entities.Kpoint;
import com.xuegao.educloud.system.server.dao.KpointDao;
import com.xuegao.educloud.system.server.service.IKpointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KpointService extends ServiceImpl<KpointDao,Kpoint> implements IKpointService {

    /**
     * 学段学科下的考点
     *
     * @param gradeId
     * @param subjectId
     * @return
     */
    @Override
    public List<Kpoint> getKpointByGradeSubject(int gradeId, int subjectId) {
        LambdaQueryWrapper<Kpoint> wrapper = Wrappers.<Kpoint>lambdaQuery()
                .eq(Kpoint::getGradeId, gradeId)
                .eq(Kpoint::getSubjectId, subjectId);

        return this.list(wrapper);
    }
}
