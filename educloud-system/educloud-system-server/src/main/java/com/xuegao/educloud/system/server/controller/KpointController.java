package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.collection.IterUtil;
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
public class KpointController {

    @Autowired
    private IKpointService kpointService;

    /**
     * 年级学科下的知识点
     * @param gradeId
     * @param subjectId
     * @return
     */
    @GetMapping("/kpoints/grade/{gradeId}/subject/{subjectId}")
    public R<List<Kpoint>> getKpoint(@PathVariable("gradeId") int gradeId, @PathVariable("subjectId") int subjectId){
        List<Kpoint> kpoints = kpointService.getKpointByGradeSubject(gradeId,subjectId);
        return R.ok(kpoints);
    }

    /**
     * 新建知识点
     * @param param
     * @return
     */
    @PostMapping("/kpoints")
    public R<Kpoint> addKpoint(@RequestBody Kpoint param){

        if(param.getGradeId() == null || param.getGradeId() <= 0){
            return R.fail("请选择专业节点");
        }
        if(param.getSubjectId() == null || param.getSubjectId() <= 0){
            return R.fail("请选择专业节点");
        }

        boolean success = kpointService.saveKpoint(param);

        return success ? R.ok() : R.fail("新建失败");

    }

    /**
     * 修改知识点名称
     * @return
     */
    @PutMapping("/kpoints/{kpointId}/name")
    public R<Kpoint> updateKpoint(@PathVariable("kpointId") int kpointId, @RequestBody Kpoint param){

        if(StringUtils.isEmpty(param.getKpointName())){
            return R.fail("请输入知识点名称");
        }

        Kpoint kpoint = new Kpoint();
        kpoint.setKpointId(kpointId);
        kpoint.setKpointName(param.getKpointName());

        boolean success = kpointService.updateById(kpoint);
        return success ? R.ok() : R.fail("修改失败");
    }


    /**
     * 批量删除知识点
     * @return
     */
    @DeleteMapping("/kpoints/batch")
    public R delKpoint(@RequestBody Map<String,List<Integer>> param){
        List<Integer> ids = param.get("ids");
        if(IterUtil.isEmpty(ids)){
            return R.fail("ID为空");
        }
        boolean success = kpointService.removeByIds(ids);
        return success ? R.ok() : R.fail("删除失败");
    }
}
