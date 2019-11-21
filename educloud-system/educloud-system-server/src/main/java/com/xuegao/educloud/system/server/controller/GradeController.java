package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.params.vo.GradeWithSubjectVO;
import com.xuegao.educloud.system.server.service.IGradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:45
 * @Description:
 */
@RestController
public class GradeController {

    @Autowired
    private IGradeService gradeService;


    /**
     * 分页查询年级
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/grades/page")
    public R getGradePage(@RequestParam(value = "pageNum",defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                          @RequestParam(value = "pageSize",defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize){
        Page<Grade> page = new Page<Grade>().setCurrent(pageNum).setSize(pageSize);
        IPage<Grade> gradePage = gradeService.getGradePage(page);
        return R.ok(gradePage);

    }

    /**
     * 批量删除年级
     * @param param ids  年级ID列表
     * @return
     */
    @DeleteMapping("/grades/batch")
    public R delGrade(@RequestBody Map<String,List<Integer>> param){

        List<Integer> ids = param.get("ids");
        if(IterUtil.isEmpty(ids)){
            return R.fail("年级ID为空");
        }

        boolean success = gradeService.removeByIds(ids);
        return success ? R.ok() : R.fail("删除年级失败");
    }

    /**
     * 修改年级
     * @param gradeId
     * @param param
     * @return
     */
    @PutMapping("/grades/{gradeId}")
    public R updateGrade(@PathVariable("gradeId") int gradeId,@RequestBody Grade param){
        if(StringUtils.isEmpty(param.getGradeName())){
            return R.fail("请输入年级名称");
        }
        param.setGradeId(gradeId);
        boolean success = gradeService.updateGrade(param);
        return success ? R.ok() : R.fail("修改年级失败");
    }

    /**
     * 新增年级
     * @return
     */
    @PostMapping("/grades")
    public R saveGrade(@RequestBody Grade param){
        if(StringUtils.isEmpty(param.getGradeName())){
            return R.fail("请输入年级名称");
        }
        boolean success = gradeService.saveGrade(param);
        return success ? R.ok() : R.fail("保存年级失败");
    }


    @GetMapping("/grades/subject")
    public R<List<GradeWithSubjectVO>> gradeWithSubject(){
        List<GradeWithSubjectVO> gradeWithSubject = gradeService.getGradesWithSubject();
        return R.ok(gradeWithSubject);
    }


    @GetMapping("/grades/ids")
    public R<List<Grade>> getGradeByIds(@RequestParam("ids") Integer[] ids){
        if(ids.length == 0){
            return R.fail("id数组为空");
        }
        List<Grade> grades = (List<Grade>) gradeService.listByIds(Arrays.asList(ids));
        return R.ok(grades);
    }
}
