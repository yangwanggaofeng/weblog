package com.study.weblog.common.exception;

/**
 * @ClassName BaseExceptionInterface
 * @Description 通用异常类接口
 * @Author zhang
 * @Date 2023/11/4
 * @Version 1.0
 **/
public interface BaseExceptionInterface {
    /**
     * 获取异常码
     * @return
     */
    String getErrorCode();

    /**
     * 获取异常信息
     * @return
     */
    String getErrorMessage();

}
