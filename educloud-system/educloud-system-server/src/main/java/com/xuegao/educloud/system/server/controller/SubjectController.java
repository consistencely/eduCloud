package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ResourceNoFoundException;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.common.response.Result;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.params.dto.SubjectGradeDTO;
import com.xuegao.educloud.system.server.service.ISubjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:45
 * @Description:
 */
@RestController
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;


    /**
     * 分页查询学科
     *
     * @return
     */
    @GetMapping("/subjects/page")
    public R<IPage<Subject>> getSubjectPage(@RequestParam(value = "pageNum", defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize) {

        Page<Subject> page = new Page<Subject>().setCurrent(pageNum).setSize(pageSize);
        IPage<Subject> subjectPage = subjectService.getSubjectPage(page);
        return R.ok(subjectPage);

    }

    /**
     * 批量删除学科
     *
     * @return
     */
    @DeleteMapping("/subjects/batch")
    public R delSubject(@RequestBody Map<String, List<Integer>> param) {

        List<Integer> ids = param.get("ids");
        if (IterUtil.isEmpty(ids)) {
            return R.fail("学科ID为空");
        }

        boolean success = subjectService.removeByIds(ids);
        return success ? R.ok() : R.fail("删除失败");
    }

    /**
     * 新增学科
     *
     * @return
     */
    @PostMapping("/subjects")
    public R saveSubject(@RequestBody Subject param) {
        if (StringUtils.isEmpty(param.getSubjectName())) {
            return R.fail("请输入学科名称");
        }
        Subject subject = new Subject();
        subject.setSubjectName(param.getSubjectName());
        subject.setSort(param.getSort());
        boolean success = subjectService.save(subject);
        return success ? R.ok() : R.fail("新增学科失败");
    }

    /**
     * 修改学科
     *
     * @return
     */
    @PutMapping("/subjects/{subjectId}")
    public R updateSubject(@PathVariable("subjectId") int subjectId, @RequestBody Subject param) {
        if (StringUtils.isEmpty(param.getSubjectName())) {
            return R.fail("请输入学科名称");
        }
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectName(param.getSubjectName());
        subject.setSort(param.getSort() != null ? param.getSort() : CommonConstants.DEFAULT_SORT);
        boolean success = subjectService.updateById(subject);
        return success ? R.ok() : R.fail("修改学科失败");
    }


    /**
     * 查询所有学科
     *
     * @return
     */
    @GetMapping("/subjects")
    public R<List<Subject>> getAllSubject() {
        List<Subject> list = subjectService.getAllSubject();
        return R.ok(list);
    }

    /**
     * 保存学科年级配置
     *
     * @return
     */
    @PostMapping("/subjects/grade")
    public R saveSubjectGrade(@RequestBody SubjectGradeDTO subjectGradeDTO) {
        if (subjectGradeDTO.getGradeId() == null) {
            return R.fail("保存失败，请重试！");
        }
        subjectService.saveSubjectGrade(subjectGradeDTO);
        return R.ok();
    }


    @GetMapping("/subjects/demo/{id}")
    public ResponseEntity<Subject> demo(@PathVariable("id") int id){

        if(id == 0){
            throw new InvalidRequestException(100,"不合法参数");
        }
        if(id == 1){
            throw new ResourceNoFoundException(404110,"不存在");
        }
        if(id == 2){
            int a = 1 / 0;
        }

        Subject subject = subjectService.getById(id);
        return ResponseEntity.ok(subject);

    }

    @GetMapping("/subjects/{id}")
    public Result<Subject> getById(@PathVariable("id") int id){
        Subject subject = subjectService.getById(id);
        return Result.success(subject);
    }


}
