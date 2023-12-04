package com.study.weblog.jwt.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName UsernameOrPasswordNullException
 * @Description 注意，需继承自 AuthenticationException，
 * 只有该类型异常，才能被后续自定义的认证失败处理器捕获到。
 * @Author zhang
 * @Date 2023/11/19
 * @Version 1.0
 **/
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
