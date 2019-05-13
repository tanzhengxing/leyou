package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author tan
 * @date 2019/5/6 23:18
 */
@Data
public class ResultException {
    private Integer code;
    private String msg;
    private Long dateTime;

    public ResultException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.dateTime = System.currentTimeMillis();
    }
}
