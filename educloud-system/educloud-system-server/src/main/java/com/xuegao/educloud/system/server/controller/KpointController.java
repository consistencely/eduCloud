package com.xuegao.educloud.system.server.controller;

import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Kpoint;
import com.xuegao.educloud.system.server.service.IKpointService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/10/30 11:39
 * @Description:
 */
@RestController
@RequestMapping("kpoint")
public class KpointController {

    @Autowired
    private IKpointService kpointService;

    /**
     * 学段学科下的考点
     * @param gradeId
     * @param subjectId
     * @return
     */
    @GetMapping("/getKpoint")
    public R<List<Kpoint>> getKpoint(@RequestParam("gradeId") int gradeId, @RequestParam(value = "subjectId") int subjectId){
        List<Kpoint> kpoints = kpointService.getKpointByGradeSubject(gradeId,subjectId);
        return R.ok(kpoints);
    }

    /**
     * 新建考点
     * @param param
     * @return
     */
    @PostMapping("/addKpoint")
    public R<Kpoint> addKpoint(@RequestBody Kpoint param){

        if(param.getGradeId() == null || param.getGradeId() <= 0){
            return R.fail("请选择专业节点");
        }
        if(param.getSubjectId() == null || param.getSubjectId() <= 0){
            return R.fail("请选择专业节点");
        }

        Kpoint kpoint = new Kpoint();
        kpoint.setKpointName("新创建考点");
        kpoint.setGradeId(param.getGradeId());
        kpoint.setSubjectId(param.getSubjectId());
        kpoint.setLevel(param.getLevel());
        kpoint.setParentId(param.getParentId());

        boolean success = kpointService.save(kpoint);

        return success ? R.ok(kpoint) : R.fail("新建失败");

    }

    /**
     * 修改考点
     * @return
     */
    @PutMapping("")
    public R<Kpoint> updateKpoint(@RequestBody Kpoint param){
        if(param.getKpointId() == null || param.getKpointId() <= 0){
            return R.fail("id不正确,修改失败");
        }
        if(StringUtils.isEmpty(param.getKpointName())){
            return R.fail("请输入考点名称");
        }

        Kpoint kpoint = new Kpoint();
        kpoint.setKpointId(param.getKpointId());
        kpoint.setKpointName(param.getKpointName());

        boolean success = kpointService.updateById(kpoint);
        return success ? R.ok(kpoint) : R.fail("修改失败");
    }


    /**
     * 删除考点
     * @return
     */
    @DeleteMapping("")
    public R delKpoint(@RequestBody int[] ids){
        boolean success = kpointService.removeByIds(Arrays.asList(ids));
        return success ? R.ok() : R.fail("删除失败");
    }
}
