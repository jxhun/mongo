package com.jxhun.mongo.exception;


import com.jxhun.mongo.vo.ResultCode;
import lombok.Data;

/**
 * 统一异常处理
 *
 * @author <a href="http://shaofan.org">Shaofan</a>
 */
@Data
public class CarabaoException extends RuntimeException {

    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误代码,如果为空，默认按照500处理
     */
    private Integer errorCode;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public CarabaoException(String msg) {
        super(msg);
        this.msg = msg;
        this.errorCode = ResultCode.DATA_PARAM_ERROR;
    }

    public CarabaoException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
