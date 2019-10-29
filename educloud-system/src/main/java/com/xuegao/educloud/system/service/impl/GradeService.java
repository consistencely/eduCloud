package com.xuegao.educloud.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.system.dao.GradeDao;
import com.xuegao.educloud.system.entities.Grade;
import com.xuegao.educloud.system.service.IGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GradeService extends ServiceImpl<GradeDao,Grade> implements IGradeService {
    @Override
    public IPage<Grade> getGradePage(Page<Grade> page) {
        LambdaQueryWrapper<Grade> wrapper = Wrappers.<Grade>lambdaQuery()
                .orderByDesc(Grade::getSort)
                .orderByAsc(Grade::getCreateTime);
        return this.page(page,wrapper);
    }

}
