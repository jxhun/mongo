package com.jxhun.mongo.vo;

/**
 * note
 *
 * @author <a href="http://shaofan.org">Shaofan</a>
 */
public class BaseOutput<T> {
    private Integer code;
    private String result;
    private T data;
    private String errorData;

    public BaseOutput() {
    }

    public BaseOutput(Integer code, String result) {
        this.code = code;
        this.result = result;
    }

    public Integer getCode() {
        return this.code;
    }

    public BaseOutput setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getResult() {
        return this.result;
    }

    public BaseOutput setResult(String result) {
        this.result = result;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public BaseOutput setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> BaseOutput<T> create(Integer code, String result) {
        return new BaseOutput(code, result);
    }

    public static <T> BaseOutput<T> success() {
        return success("OK");
    }

    public static <T> BaseOutput<T> success(String msg) {
        return create(ResultCode.OK, msg);
    }

    public static <T> BaseOutput<T> failure() {
        return failure("操作失败!");
    }

    public static <T> BaseOutput<T> failure(String msg) {
        return create(ResultCode.DATA_PARAM_ERROR, msg);
    }

    public static <T> BaseOutput<T> failure(Integer code, String msg) {
        return create(code, msg);
    }

    public String getErrorData() {
        return this.errorData;
    }

    public BaseOutput setErrorData(String errorData) {
        this.errorData = errorData;
        return this;
    }

    public boolean isSuccess() {
        return ResultCode.OK.equals(this.code);
    }
}
