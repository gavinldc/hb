package com.dc.hb.facade.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import com.dc.hb.dtos.exception.LogicError;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class AdviceController {


    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<BaseResult> handleError(Exception e) {
        String errorCode = String.valueOf((int)((Math.random()*9+1)*1000));
        log.error("exception error Code:[{}]",errorCode, e);
        return ResponseEntity.ok(new FailResult("999", "系统异常",errorCode));
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler({LogicError.class})
    public ResponseEntity<BaseResult> handleError(LogicError e) {
        String errorCode =String.valueOf((int)((Math.random()*9+1)*1000));
        log.warn("exception error Code:[{}]",errorCode, e);
        return ResponseEntity.ok(new FailResult(e.getCode(), e.getMessage()));
    }

    /**
     * 数据绑定或校验出错
     * @param e
     * @return
     */
    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<BaseResult> handleError(WebExchangeBindException e) {
        String errorCode = String.valueOf((int)((Math.random()*9+1)*1000));
        log.error("exception error Code:[{}]",errorCode, e);
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuffer errMesageBuffer = new StringBuffer();
        fieldErrors.stream().forEach(error -> {
            //bindingFailure=true时是数据类型错误
            if (error.isBindingFailure()) {
                errMesageBuffer.append(";" + error.getField() + "格式错误");
            } else { //约束类错误
                errMesageBuffer.append(";" + error.getDefaultMessage());
            }
        });
        return ResponseEntity.ok(new FailResult("999", errMesageBuffer.deleteCharAt(0).toString(),errorCode));
    }


    /**
     * 输入数据有误
     * @param e
     * @return
     */
    @ExceptionHandler({ServerWebInputException.class})
    public ResponseEntity<BaseResult> handleError(ServerWebInputException e) {
        String errorCode = String.valueOf((int)((Math.random()*9+1)*1000));
        log.error("exception error Code:[{}]",errorCode, e);
        return ResponseEntity.ok(new FailResult("999", "系统异常",errorCode));
    }

    /**
     *  contentType出错
     */
    @ExceptionHandler({UnsupportedMediaTypeStatusException.class})
    public ResponseEntity<BaseResult> handleError(UnsupportedMediaTypeStatusException e) {
        String errorCode =  String.valueOf((int)((Math.random()*9+1)*1000));
        log.error("exception error Code:[{}]",errorCode, e);
        return ResponseEntity.ok(new FailResult("999", "系统异常",errorCode));
    }
}
