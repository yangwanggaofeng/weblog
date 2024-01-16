package com.study.weblog.web.controller;

import com.study.weblog.common.utils.Response;
import com.study.weblog.jwt.filter.JwtAuthenticationFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName loginController
 * @Description TODO
 * @Author zhang
 * @Date 2024/1/5
 * @Version 1.0
 **/
@RestController
@ApiModel("登录接口")
public class loginController {
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/login")
    @ApiOperation("获取token")
    public Response getToken(@RequestBody User user) throws ServletException, IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("username",user.getUsername());
        request.setAttribute("password",user.getPassword());
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        Authentication authentication = jwtAuthenticationFilter.attemptAuthentication(request, null);
        return Response.success(authentication);
    }
}
