package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tan
 * @date 2019/5/6 22:50
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ResultException> handlerException(LyException e){
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return  ResponseEntity.status(exceptionEnum.getCode()).body(new ResultException(exceptionEnum));
    }
}
