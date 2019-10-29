package com.xuegao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.entities.Grade;
import com.xuegao.entities.Subject;
import com.xuegao.params.dto.SubjectGradeDTO;

import java.util.List;

public interface IGradeService extends IService<Grade> {
    IPage<Grade> getGradePage(Page<Grade> page);

}
