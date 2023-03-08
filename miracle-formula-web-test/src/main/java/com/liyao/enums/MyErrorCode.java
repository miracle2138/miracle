package com.liyao.enums;

import com.spicdt.formula.framework.common.exception.IErrorCode;
import com.spicdt.formula.framework.common.exception.enums.ServiceErrorCodeRange;

import org.springframework.http.HttpStatus;

/**
 * @Author 栗垚
 * @Date 2023/3/7
 */
public enum MyErrorCode implements IErrorCode {
    ProjectNotExist(1_001_001_001, "项目: {} 不存在"),
    ;
    /**
     * Http code
     */
    private final Integer httpCode;

    /**
     * 业务错误码
     */
    private final Integer code;

    /**
     * 错误提示默认值，优先使用国际化资源文件中的值
     */
    private final String msg;

    MyErrorCode(Integer code, String message) {
        this(HttpStatus.OK.value(), code, message);
    }

    MyErrorCode(Integer httpCode, Integer code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.msg = message;
    }

    @Override
    public Integer getHttpCode() {
        return this.httpCode;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public ServiceErrorCodeRange getCodeRange() {
        return null;
    }
//
//    @Override
//    public ServiceErrorCodeRange getCodeRange() {
//        return null;
//    }
}
