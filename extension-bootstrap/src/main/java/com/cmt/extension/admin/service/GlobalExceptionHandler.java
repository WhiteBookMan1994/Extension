package com.cmt.extension.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.ctrip.framework.apollo.openapi.client.exception.ApolloOpenApiException;
import com.cmt.extension.admin.model.BusinessException;
import com.cmt.extension.admin.model.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常拦截
 *
 * @author tuzhenxian
 * @date 19-10-11
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleValidationException(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(";");
        }
        return Result.fail(sb.toString());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result handleBusinessException(HttpServletRequest req, BusinessException e) {
        log.error("", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleGlobalException(HttpServletRequest request, Exception e) {
        log.error("", e);
        if (e.getCause() instanceof ApolloOpenApiException) {
            ApolloOpenApiException ae = (ApolloOpenApiException) e.getCause();
            return Result.fail(ae.getMessage());
        }
        return Result.fail("系统异常");
    }
}
