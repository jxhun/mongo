package com.jxhun.mongo.exception;

import com.jxhun.mongo.vo.BaseOutput;
import com.jxhun.mongo.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截器
 *
 * @author <a href="http://shaofan.org">Shaofan</a>
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    private final static Boolean PRINT_STACK = false;

    @ExceptionHandler(CarabaoException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public BaseOutput<Object> handleCarabaoException(CarabaoException e) {

        String errorMsg = "Carabao exception";
        Integer code = ResultCode.DATA_PARAM_ERROR;
        if (e != null) {
            errorMsg = e.getMsg();
            code = e.getErrorCode();
            if (PRINT_STACK) {
                e.printStackTrace();
            } else {
                log.error(e.toString(), e);
            }
        }

        return BaseOutput.failure(code, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public BaseOutput<Object> handleException(Exception e) {
        if (e != null) {
            if (PRINT_STACK) {
                e.printStackTrace();
            } else {
                log.error(e.toString(), e);
            }
        }
        return BaseOutput.failure(ResultCode.DATA_PARAM_ERROR, "操作失败，系统异常");
    }
}
