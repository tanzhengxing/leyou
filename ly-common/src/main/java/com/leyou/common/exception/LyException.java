package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tan
 * @date 2019/5/6 23:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
