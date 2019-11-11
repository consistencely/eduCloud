package com.xuegao.educloud.system.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.params.dto.SubjectGradeDTO;
import com.xuegao.educloud.system.server.service.ISubjectService;
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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;


    /**
     * 分页查询学科
     * @param current
     * @return
     */
    @GetMapping("/page/{curr}")
    public R<IPage<Subject>> getSubjectPage(@PathVariable("curr") int current){
        Page<Subject> page = new Page<Subject>().setCurrent(current);
        IPage<Subject> subjectPage = subjectService.getSubjectPage(page);
        return R.ok(subjectPage);

    }

    /**
     * 删除学科
     * @return
     */
    @DeleteMapping("/{id}")
    public R delSubject(@PathVariable("id") int id){
        boolean success = subjectService.removeById(id);
        return success ? R.ok() : R.fail("删除失败");
    }

    /**
     * 新增/修改学科
     * @return
     */
    @PostMapping("")
    public R saveSubject(@RequestBody Subject param){
        if(StringUtils.isEmpty(param.getSubjectName())){
            return R.fail("请输入学科名称");
        }
        Subject subject = new Subject();
        subject.setSubjectId(param.getSubjectId());
        subject.setSubjectName(param.getSubjectName());
        subject.setSort(param.getSort());
        boolean success = subjectService.saveOrUpdate(subject);
        return success ? R.ok() : R.fail("保存失败");
    }

    /**
     * 查询所有学科
     * @return
     */
    @GetMapping("/all")
    public R<List<Subject>> getAllSubject(){
        List<Subject> list = subjectService.getAllSubject();
        return R.ok(list);
    }

    /**
     * 保存学科年级配置
     * @return
     */
    @PostMapping("/saveSubjectGrade")
    public R saveSubjectGrade(@RequestBody SubjectGradeDTO subjectGradeDTO){
        if(subjectGradeDTO.getGradeId() == null ){
            return R.fail("保存失败，请重试！");
        }
        subjectService.saveSubjectGrade(subjectGradeDTO);
        return R.ok();
    }

}
