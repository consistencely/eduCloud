package com.xuegao.educloud.system.server.service.impl;

import cn.hutool.core.util.StrUtil;
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

    /**
     * 新增知识点
     *
     * @param param
     * @return
     */
    @Override
    public boolean saveKpoint(Kpoint param) {

        Kpoint kpoint = new Kpoint();
        String name = StrUtil.isEmpty(param.getKpointName()) ? "新创建知识点" : param.getKpointName();
        kpoint.setKpointName(name);
        kpoint.setGradeId(param.getGradeId());
        kpoint.setSubjectId(param.getSubjectId());
        Kpoint pkpoint = baseMapper.selectById(param.getParentId());
        if(pkpoint != null){
            kpoint.setLevel(pkpoint.getLevel() + 1);
            kpoint.setParentId(param.getParentId());
            StringBuilder pathsb = new StringBuilder(pkpoint.getPath()).append(pkpoint.getKpointId()).append("/");
            kpoint.setPath(pathsb.toString());
        }

        return this.save(kpoint);
    }

}
