package com.xuegao.educloud.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: LIM
 * @Date: 2019/11/18 15:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ErrorResource {

    private Integer code;
    private String message;
}
