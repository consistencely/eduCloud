package com.xuegao.educloud.system.client.params.vo;

import com.xuegao.educloud.system.client.entities.Subject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/10/30 10:53
 * @Description:
 */
@Data
public class GradeWithSubjectVO {

    /** 学段ID */
    private Integer id;

    /** 学段名称 */
    private String name;

    /** 所属学科列表 */
    private List<Subject> subjects = new ArrayList<>();

    public GradeWithSubjectVO(){

    }

    public GradeWithSubjectVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
