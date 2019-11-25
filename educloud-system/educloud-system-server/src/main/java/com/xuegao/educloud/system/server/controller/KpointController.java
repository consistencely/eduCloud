package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.collection.IterUtil;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Kpoint;
import com.xuegao.educloud.system.server.service.IKpointService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/10/30 11:39
 * @Description:
 */
@RestController
@RequestMapping("kpoints")
public class KpointController {

    @Autowired
    private IKpointService kpointService;

    /**
     * 年级学科下的知识点
     * @param gradeId
     * @param subjectId
     * @return
     */
    @GetMapping("/grade/{gradeId}/subject/{subjectId}")
    public List<Kpoint> getKpoint(@PathVariable("gradeId") int gradeId, @PathVariable("subjectId") int subjectId){
        return kpointService.getKpointByGradeSubject(gradeId,subjectId);
    }

    /**
     * 新建知识点
     * @param param
     * @return
     */
    @PostMapping
    public boolean addKpoint(@RequestBody Kpoint param){

        if(param.getGradeId() == null || param.getGradeId() <= 0){
            throw new InvalidRequestException("年级不能为空");
        }
        if(param.getSubjectId() == null || param.getSubjectId() <= 0){
            throw new InvalidRequestException("学科不能为空");
        }

        return kpointService.saveKpoint(param);

    }

    /**
     * 修改知识点名称
     * @return
     */
    @PutMapping("/{kpointId}/name")
    public boolean updateKpoint(@PathVariable("kpointId") int kpointId, @RequestBody Kpoint param){

        if(StringUtils.isEmpty(param.getKpointName())){
            throw new InvalidRequestException("知识点名称不能为空");
        }

        Kpoint kpoint = new Kpoint();
        kpoint.setKpointId(kpointId);
        kpoint.setKpointName(param.getKpointName());

        return kpointService.updateById(kpoint);
    }


    /**
     * 批量删除知识点
     * @return
     */
    @DeleteMapping("/batch")
    public boolean delKpoint(@RequestBody Map<String,List<Integer>> param){
        List<Integer> ids = param.get("ids");
        if(IterUtil.isEmpty(ids)){
            throw new InvalidRequestException("知识点ID不能为空");
        }
        return kpointService.removeByIds(ids);
    }
}
