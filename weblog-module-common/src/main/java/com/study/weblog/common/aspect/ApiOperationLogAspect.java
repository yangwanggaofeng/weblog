package com.study.weblog.common.aspect;

import com.study.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName ApiOperationLogAspect
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/1
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {
    /**
     * 以自定义注解@ApiOperationLog为切点，凡是添加@ApiOperationLog注解的方法，都会执行环绕通知的代码
     */
    @Pointcut("@annotation(com.study.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            //请求开始时间
            long startTime = System.currentTimeMillis();
            //MDC traceId 表示跟踪 ID， 值这里直接用的 UUID
            MDC.put("traceId", UUID.randomUUID().toString());
            //获取被请求类的名称和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            //请求入参
            Object [] args = joinPoint.getArgs();
            //入参转json字符串
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(","));
            //功能描述信息
            String description = getApiOperationDescription(joinPoint);
            //打印请求相关参数
            log.info("====请求开始：[{}],入参：{},请求类：{},请求方法：{}===================================",
                    description, argsJsonStr, className, methodName);
            //执行切点方法
            Object result = joinPoint.proceed();
            //执行耗时
            long executionTime = System.currentTimeMillis() - startTime;
            //打印出参等相关信息
            log.info("====请求结束：[{}],耗时：{},出参：{}===================================",
                    description, executionTime, JsonUtil.toJsonString(result));
            return result;
        }finally {
                MDC.clear();
        }

    }

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */
    public String getApiOperationDescription(ProceedingJoinPoint joinPoint){
        //1.从ProceedingJoinPoint中获取MethodSignature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //2.使用MethodSignature获取当前注解的method
        Method method = methodSignature.getMethod();
        //3.从method中提取ApiOperationLog注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
        //4.从ApiOperationLog注解中获取descriptino属性
        return apiOperationLog.description();
    }

    /**
     * Object转json字符串
     * @return
     */
    private Function<Object,String> toJsonStr(){
        return args -> JsonUtil.toJsonString(args);
    }
}
