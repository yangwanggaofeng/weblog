package com.study.weblog.web;

import com.study.weblog.common.domain.dos.UserDo;
import com.study.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void testLog(){
        log.info("this is info 日志");
        log.warn("this is warn 日志");
        log.error("this is error 日志");
        String str = "占位符";
        log.info("this is {} 日志", str);
    }

    @Test
    void inserTestUser(){
        UserDo userDo = UserDo.builder()
                .username("xiaoqi")
                .password("$2a$10$r7hyQNFuLlFFGS38dZX6H.pBQE4zWH4kmrwiCMeaoKrrT9XmL4Rhm")
                .createTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(userDo);
    }


}
