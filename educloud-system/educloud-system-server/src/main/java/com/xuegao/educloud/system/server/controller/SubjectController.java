package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ResourceNoFoundException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Subject;
import com.xuegao.educloud.system.client.params.dto.SubjectDTO;
import com.xuegao.educloud.system.client.params.dto.SubjectGradeDTO;
import com.xuegao.educloud.system.server.enums.SysExceptionEnum;
import com.xuegao.educloud.system.server.service.ISubjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:45
 * @Description:
 */
@RestController
@RequestMapping("subjects")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    /**
     * 分页查询学科
     *
     * @return
     */
    @GetMapping("/page")
    public IPage<Subject> getSubjectPage(@RequestParam(value = "pageNum", defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize) {

        Page<Subject> page = new Page<Subject>().setCurrent(pageNum).setSize(pageSize);
        return subjectService.getSubjectPage(page);

    }

    /**
     * 批量删除学科
     *
     * @return
     */
    @DeleteMapping("/batch")
    public boolean delSubject(@RequestBody Map<String, List<Integer>> param) {

        List<Integer> ids = param.get("ids");
        if (IterUtil.isEmpty(ids)) {
            throw new InvalidRequestException();
        }

        return subjectService.removeByIds(ids);
    }

    /**
     * 新增学科
     *
     * @return
     */
    @PostMapping
    public boolean saveSubject(@Valid @RequestBody SubjectDTO param) {
        Subject subject = new Subject();
        subject.setSubjectName(param.getSubjectName());
        subject.setSort(param.getSort());
        return subjectService.save(subject);
    }

    /**
     * 修改学科
     *
     * @return
     */
    @PutMapping("/{subjectId}")
    public boolean updateSubject(@PathVariable("subjectId") int subjectId,@Valid @RequestBody SubjectDTO param) {
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectName(param.getSubjectName());
        subject.setSort(param.getSort() != null ? param.getSort() : CommonConstants.DEFAULT_SORT);
        return subjectService.updateById(subject);
    }


    /**
     * 查询所有学科
     *
     * @return
     */
    @GetMapping
    public List<Subject> getAllSubject() {
        return subjectService.getAllSubject();
    }

    /**
     * 保存学科年级配置
     *
     * @return
     */
    @PostMapping("/grade")
    public boolean saveSubjectGrade(@RequestBody SubjectGradeDTO subjectGradeDTO) {
        return subjectService.saveSubjectGrade(subjectGradeDTO);
    }



}
