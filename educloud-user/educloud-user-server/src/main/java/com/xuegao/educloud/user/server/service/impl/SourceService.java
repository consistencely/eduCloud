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
    public IPage<SourceDTO> getSourcePage(Page<SourceDTO> page, SourceDTO sourceDTO) {
        return baseMapper.getSourcePage(page,sourceDTO);
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
    public Integer updateSource(SourceDTO sourceDTO) {
        LambdaUpdateWrapper<Source> updateWrapper = Wrappers.<Source>lambdaUpdate()
                .eq(Source::getSourceId,sourceDTO.getSourceId())
                .set(Source::getSourceName,sourceDTO.getSourceName())
                .set(Source::getApplyWay,sourceDTO.getApplyWay())
                .set(Source::getPerson,sourceDTO.getPerson())
                .set(Source::getCity,sourceDTO.getCity())
                .set(Source::getProvince,sourceDTO.getProvince())
                .set(Source::getCounty,sourceDTO.getCounty())
                .set(Source::getAddrDetail,sourceDTO.getAddrDetail())
                .set(Source::getTel,sourceDTO.getTel());
        return baseMapper.update(null,updateWrapper);
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

    @Override
    public Integer saveSource(SourceDTO sourceDTO) {
        Source source = new Source()
                .setSourceName(sourceDTO.getSourceName()).setApplyWay(sourceDTO.getApplyWay())
                .setCity(sourceDTO.getCity()).setCounty(sourceDTO.getCounty()).setProvince(sourceDTO.getProvince())
                .setAddrDetail(sourceDTO.getAddrDetail()).setTel(sourceDTO.getTel()).setPerson(sourceDTO.getPerson());
        return baseMapper.insert(source);
    }
}
