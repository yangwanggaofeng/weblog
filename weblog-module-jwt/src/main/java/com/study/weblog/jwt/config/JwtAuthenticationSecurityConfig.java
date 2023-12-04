package com.study.weblog.jwt.config;

import com.study.weblog.jwt.filter.JwtAuthenticationFilter;
import com.study.weblog.jwt.handler.RestAuthenticationFailureHandler;
import com.study.weblog.jwt.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @ClassName JwtAuthenticationSecurityConfig
 * @Description 一个 Spring Security 配置类，用于配置 JWT（JSON Web Token）的身份验证机制。
 * 它继承了 Spring Security 的 SecurityConfigurerAdapter 类，用于在 Spring Security 配置中
 * 添加自定义的认证过滤器和提供者。通过重写 configure() 方法，
 * 将写好过滤器、认证成功、失败处理器，以及加密算法整合到了 httpSecurity 中。
 * @Author zhang
 * @Date 2023/11/19
 * @Version 1.0
 **/
@Configuration
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 自定义的用于 JWT 身份验证的过滤器
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));

        // 设置登录认证对应的处理类（成功处理、失败处理）
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);

        // 直接使用 DaoAuthenticationProvider, 它是 Spring Security 提供的默认的身份验证提供者之一
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置 userDetailService，用于获取用户的详细信息
        provider.setUserDetailsService(userDetailsService);
        // 设置加密算法
        provider.setPasswordEncoder(passwordEncoder);
        httpSecurity.authenticationProvider(provider);
        // 将这个过滤器添加到 UsernamePasswordAuthenticationFilter 之前执行
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
