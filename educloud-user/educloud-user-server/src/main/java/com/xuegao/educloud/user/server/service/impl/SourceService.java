package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Source;
import com.xuegao.educloud.user.client.params.dto.SourceDTO;
import com.xuegao.educloud.user.server.dao.SourceDao;
import com.xuegao.educloud.user.server.service.ISourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: ZWei
 * @Date: 2019/11/13 14:43
 * @Description:
 */
@Service
public class SourceService extends ServiceImpl<SourceDao, Source> implements ISourceService {

    @Override
    public IPage<SourceDTO> getSourcePage(Page<SourceDTO> page, Source source) {
        return baseMapper.getSourcePage(page,source);
    }

    @Override
    public List<Source> getSourceList() {
        LambdaQueryWrapper<Source> queryWrapper = Wrappers.<Source>lambdaQuery()
                .eq(Source::getIsDel, 0)
                .select(Source::getSourceId, Source::getSourceName)
                .groupBy(Source::getSourceName);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Integer updateSource(Source source) {
        LambdaUpdateWrapper<Source> updateWrapper = Wrappers.<Source>lambdaUpdate()
                .eq(Source::getSourceId,source.getSourceId())
                .set(Source::getSourceName,source.getSourceName())
                .set(Source::getApplyWay,source.getApplyWay())
                .set(Source::getPerson,source.getPerson())
                .set(Source::getCity,source.getCity())
                .set(Source::getProvince,source.getProvince())
                .set(Source::getCounty,source.getCounty())
                .set(Source::getAddrDetail,source.getAddrDetail())
                .set(Source::getTel,source.getTel());
        return baseMapper.update(source,updateWrapper);
    }

    @Override
    public Source getSourceInfo(Integer sourceId) {
        LambdaQueryWrapper<Source> queryWrapper = Wrappers.<Source>lambdaQuery()
                .eq(Source::getIsDel,0)
                .eq(Source::getSourceId,sourceId)
                .select(Source::getSourceId,Source::getSourceName,Source::getApplyWay,
                        Source::getPerson,Source::getCity,Source::getProvince,
                        Source::getCounty,Source::getAddrDetail,Source::getTel);
        return baseMapper.selectOne(queryWrapper);
    }
}
