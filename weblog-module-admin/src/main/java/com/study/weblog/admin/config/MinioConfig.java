package com.study.weblog.admin.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MinioConfig
 * @Description TODO
 * @Author zhang
 * @Date 2024/2/22
 * @Version 1.0
 **/
@Configuration
public class MinioConfig {
    @Autowired
    private MinioProperties minioProperties;
    @Bean
    public MinioClient minioClient(){
        //构建Minio客户端
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
