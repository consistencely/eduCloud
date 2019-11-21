package com.xuegao.educloud.common.exception.resource;

import lombok.*;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResource {

    private Integer code;
    private String message;
}
