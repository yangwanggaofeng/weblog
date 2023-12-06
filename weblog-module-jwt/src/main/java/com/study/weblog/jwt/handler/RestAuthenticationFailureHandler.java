package com.study.weblog.jwt.handler;

import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.utils.Response;
import com.study.weblog.jwt.exception.UsernameOrPasswordNullException;
import com.study.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RestAuthenticationFailureHandler
 * @Description 认证失败处理器
 * @Author zhang
 * @Date 2023/11/19
 * @Version 1.0
 **/
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("AuthenticationException: ", exception);
        if(exception instanceof UsernameOrPasswordNullException ){
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        }else if(exception instanceof BadCredentialsException){
            ResultUtil.fail(response, Response.fail(ResponseCodeEnum
                    .USERNAME_OR_PWD_ERROR.getErrorMessage()));
        }
        // 登录失败
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.LOGIN_FAIL));
    }
}
