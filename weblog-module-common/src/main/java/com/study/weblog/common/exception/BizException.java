package com.study.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName BizException
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/4
 * @Version 1.0
 **/
@Getter
@Setter
public class BizException extends RuntimeException{
    //错误代码
    private String errorCode;
    //错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface){
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
