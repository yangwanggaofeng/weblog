package com.study.weblog.web.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName User
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/2
 * @Version 1.0
 **/
@Data
public class User {
    /**
     * 用户名
     */
    private String username;
    /**
     * 性别
     */
    private Integer sex;
}
