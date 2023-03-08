package com.liyao;


import com.spicdt.formula.framework.common.exception.ServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@Slf4j
//@RestControllerAdvice("com.liyao")
public class GlobalExHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        log.info("数据验证异常：{}", error.getDefaultMessage());
        return error.getDefaultMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public String validateErrorHandler1(ServiceException e) {
        log.info("服务器异常：{}", e.getMessage());
        return e.getMessage();
    }
}
