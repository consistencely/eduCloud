package com.xuegao.educloud.common.exception;

import com.xuegao.educloud.common.params.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码：
 * 400XX    参数错误
 * 404XX    未找到数据
 */
public class ErrorCode {

    /**
     * 参数错误
     */
    public static final int Error_400 = 400;

    /**
     * 手机号码错误
     */
    public static final int Error_40001 = 40001;

    /**
     * 密码错误
     */
    public static final int Error_40002 = 40002;




    private Map<Integer, String> msgs;

    private static ErrorCode errorCode;

    private ErrorCode() {
        msgs = new HashMap<>();
        msgs.put(Error_400, "参数错误");
        msgs.put(Error_40001, "手机号码错误");
        msgs.put(Error_40002, "密码错误");
    }

    public static ErrorCode getInstance() {
        if (errorCode == null) {
            errorCode = new ErrorCode();
        }
        return errorCode;
    }

    public String getMessage(Integer key) {
        return msgs.get(key);
    }

    /**
     * @param code
     * @return
     */
    public R getErrorVo(int code) {
        return R.fail(code, null, msgs.get(code));
    }

}
