package com.xuegao.educloud.system.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.params.vo.GradeWithSubjectVO;
import com.xuegao.educloud.system.server.service.IGradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:45
 * @Description:
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;


    /**
     * 分页查询学段
     * @param current 页数
     * @return
     */
    @GetMapping("/page/{curr}")
    public R getGradePage(@PathVariable("curr") int current){
        Page<Grade> page = new Page<Grade>().setCurrent(current);
        IPage<Grade> gradePage = gradeService.getGradePage(page);
        return R.ok(gradePage);

    }

    /**
     * 删除学段
     * @return
     */
    @DeleteMapping("/{id}")
    public R delGrade(@PathVariable("id") int id){
        boolean success = gradeService.removeById(id);
        return success ? R.ok() : R.fail("删除失败");
    }

    /**
     * 新增/修改学段
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public R saveGrade(@RequestBody Grade param){
        if(StringUtils.isEmpty(param.getName())){
            return R.fail("请输入学段名称");
        }
        Grade grade = new Grade();
        grade.setId(param.getId());
        grade.setName(param.getName());
        grade.setSort(param.getSort());
        boolean success = gradeService.saveOrUpdate(grade);
        return success ? R.ok() : R.fail("保存失败");
    }

    @GetMapping("/gradeWithSubject")
    public R<List<GradeWithSubjectVO>> gradeWithSubject(){
        List<GradeWithSubjectVO> gradeWithSubject = gradeService.getGradesWithSubject();
        return R.ok(gradeWithSubject);
    }


}
