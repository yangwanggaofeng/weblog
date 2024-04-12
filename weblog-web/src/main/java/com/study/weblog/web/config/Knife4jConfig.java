package com.study.weblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @ClassName Knife4jConfig
 * @Description knife4j配置类
 * @Author zhang
 * @Date 2023/11/5
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2WebMvc
@Profile("sit")
//@Profile("dev")
public class Knife4jConfig {
    @Bean("adminApi")
    public Docket createApiDoc(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                //分组名称
                .groupName("weblog 前台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.weblog.web.controller"))//指定controller的扫描包路径
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建api信息
     * @return
     */
    public ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("Weblog 博客前端接口文档")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")//描述
                .termsOfServiceUrl("https://www.quanxiaoha.com/")//Api服务条款
                .contact(new Contact("zhang","www.test.com","1415589595@qq.com"))//联系人
                .version("1.0")//版本
                .build();
    }
}
