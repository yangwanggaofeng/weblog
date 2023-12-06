package com.study.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * @ClassName ApiOperationLog
 * @Description TODO
 * @Author zhang
 * @Date 2023/10/31
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiOperationLog {
    /**
     * Api功能描述
     */
    String description() default "";
}
