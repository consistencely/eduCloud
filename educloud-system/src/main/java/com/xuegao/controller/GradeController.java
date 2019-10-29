package com.xuegao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.entities.Grade;
import com.xuegao.params.Result;
import com.xuegao.params.dto.SubjectGradeDTO;
import com.xuegao.service.IGradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param current
     * @return
     */
    @PostMapping("/gradePage/{curr}")
    public Result getGradePage(@PathVariable("curr") int current){
        Page<Grade> page = new Page<Grade>().setCurrent(current);
        IPage<Grade> gradePage = gradeService.getGradePage(page);
        return Result.success(gradePage);

    }

    /**
     * 删除学段
     * @return
     */
    @PostMapping("/del/{id}")
    public Result delGrade(@PathVariable("id") int id){
        boolean success = gradeService.removeById(id);
        return success ? Result.success() : Result.fail("删除失败");
    }

    /**
     * 新增/修改学段
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveGrade(@RequestBody Grade param){
        if(StringUtils.isEmpty(param.getName())){
            return Result.fail("请输入学段名称");
        }
        Grade grade = new Grade();
        grade.setId(param.getId());
        grade.setName(param.getName());
        grade.setSort(param.getSort());
        boolean success = gradeService.saveOrUpdate(grade);
        return success ? Result.success() : Result.fail("保存失败");
    }


}
