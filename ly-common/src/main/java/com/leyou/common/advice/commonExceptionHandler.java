package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.LyExceptionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice //#拦截设置controller
public class commonExceptionHandler {


    @ExceptionHandler(LyException.class)
    public ResponseEntity handleException(LyException e){
        ExceptionEnum em=e.getExceptionEnum();
        LyExceptionResult result = new LyExceptionResult();
        result.setMsg(em.getMsg());
        return ResponseEntity.status(em.getCode()).body(result);
    }
}
