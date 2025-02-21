package org.oyproj.exception;

import com.mysql.cj.util.StringUtils;
import org.oyproj.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(e.getMessage()!=null&&e.getMessage().length()!=0 ?
                e.getMessage():"optional exception");

    }
}
