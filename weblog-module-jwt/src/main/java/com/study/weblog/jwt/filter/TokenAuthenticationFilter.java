package com.study.weblog.jwt.filter;

import com.study.weblog.jwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName TokenAuthenticationFilter
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/20
 * @Version 1.0
 **/
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.tokenPrefix}")    
    private String tokenPrefix;
    @Value("${jwt.tokenHeaderKey}")
    private String tokenHeaderKey;
    @Resource
    private JwtTokenHelper jwtTokenHelper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取key为Authorization的值 token请求头中的key
        // String header = request.getHeader("Authorization");
        String requestURL = request.getRequestURI();
        if(requestURL.startsWith("/admin")){
            String header = request.getHeader(tokenHeaderKey);
            //判断value值是否以Bearer开头
            if(StringUtils.startsWith(header, tokenPrefix)){
                //截取token令牌
                String token = StringUtils.substring(header, 7);
                log.info("Token :{}", token);
                if(StringUtils.isNotBlank(token)){
                    try {
                        jwtTokenHelper.parseToken(token);
                    }catch (UnsupportedJwtException | MalformedJwtException | SignatureException |IllegalArgumentException e){
                        authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token非法"));
                        return ;
                    }catch (ExpiredJwtException e){
                        authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    }
                    //从token中解析出用户名
                    String username = jwtTokenHelper.getUsernameByToken(token);
                    if(StringUtils.isNotBlank(username)
                            && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
                        //根据用户名获取用户详情信息
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        //将用户信息存入authentication，方便后续校验
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //将authencation 存入ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }
                }
            }
        }

        //继续执行下一个过滤器
        filterChain.doFilter(request, response);
    }
}
