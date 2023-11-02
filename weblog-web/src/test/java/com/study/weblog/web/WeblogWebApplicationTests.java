package com.study.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

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


}
