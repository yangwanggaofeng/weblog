package com.study.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/16
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.study.weblog.common.domain.mapper")
public class MybatisPlusConfig {
}
