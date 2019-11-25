package com.xuegao.educloud.system.server.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.params.dto.GradeDTO;
import com.xuegao.educloud.system.client.params.vo.GradeWithSubjectVO;
import com.xuegao.educloud.system.server.service.IGradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: LIM
 * @Date: 2019/10/28 15:45
 * @Description:
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private IGradeService gradeService;


    /**
     * 分页查询年级
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/page")
    public IPage<Grade> getGradePage(@RequestParam(value = "pageNum",defaultValue = CommonConstants.FIRST_PAGE) int pageNum,
                          @RequestParam(value = "pageSize",defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) int pageSize){
        Page<Grade> page = new Page<Grade>().setCurrent(pageNum).setSize(pageSize);
        return gradeService.getGradePage(page);

    }

    /**
     * 批量删除年级
     * @param param ids  年级ID列表
     * @return
     */
    @DeleteMapping("/batch")
    public boolean delGrade(@RequestBody Map<String,List<Integer>> param){
        List<Integer> ids = param.get("ids");
        if(IterUtil.isEmpty(ids)){
            throw new InvalidRequestException("年级ID不能为空");
        }
        return gradeService.removeByIds(ids);
    }

    /**
     * 修改年级
     * @param gradeId
     * @param param
     * @return
     */
    @PutMapping("/{gradeId}")
    public boolean updateGrade(@PathVariable("gradeId") int gradeId,@Valid @RequestBody GradeDTO param){
        Grade grade = new Grade();
        BeanUtils.copyProperties(param,grade);
        grade.setGradeId(gradeId);
        return gradeService.updateGrade(grade);
    }

    /**
     * 新增年级
     * @return
     */
    @PostMapping
    public boolean saveGrade(@Valid @RequestBody GradeDTO param){
        return gradeService.saveGrade(param);
    }


    @GetMapping("/subject")
    public List<GradeWithSubjectVO> gradeWithSubject(){
        return gradeService.getGradesWithSubject();
    }


    @GetMapping("/ids")
    public List<Grade> getGradeByIds(@RequestParam("ids") Integer[] ids){
        if(ids.length == 0){
            throw new InvalidRequestException("年级ID不能为空");
        }
        return (List<Grade>) gradeService.listByIds(Arrays.asList(ids));
}
}
