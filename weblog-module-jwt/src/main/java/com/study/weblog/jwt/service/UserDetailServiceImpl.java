package com.study.weblog.jwt.service;

import com.study.weblog.common.domain.dos.UserDo;
import com.study.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName UserDetailServiceImpl
 * @Description UserDetailsService 是 Spring Security 提供的接口，
 * 用于从应用程序的数据源（如数据库、LDAP、内存等）中加载用户信息。
 * 它是一个用于将用户详情加载到 Spring Security 的中心机制。UserDetailsService 主要负责两项工作： *
 * 1.加载用户信息： 从数据源中加载用户的用户名、密码和角色等信息。
 * 2.创建 UserDetails 对象： 根据加载的用户信息，创建一个 Spring Security 所需的 UserDetails 对象，
 * 包含用户名、密码、角色和权限等。
 * @Author zhang
 * @Date 2023/11/18
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    // authorities 用于指定角色，这里写死为 ADMIN 管理员
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDo userDo = userMapper.findByUserName(username);//// 从数据库中查询
        if(Objects.isNull(userDo)){
            throw  new UsernameNotFoundException("该用户不存在");
        }
        return User.withUsername(userDo.getUsername())
                .password(userDo.getPassword())
                .authorities("ADMIN")
                .build();
    }
}
