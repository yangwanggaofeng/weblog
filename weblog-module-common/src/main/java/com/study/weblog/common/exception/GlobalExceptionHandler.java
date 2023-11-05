package com.study.weblog.common.exception;

import com.study.weblog.common.enums.ResponseCodeEnum;
import com.study.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * 上述代码中，通过 @ControllerAdvice 注解，我们将 GlobalExceptionHandler 声明为了全局异常处理类。
 * 在其中，定义了一个 handleBizException() 方法，并通过 @ExceptionHandler 注解指定只捕获 BizException 自定义业务异常。
 * 然后，打印了相关错误日志，并组合了统一的响应格式返回。
 * @Author zhang
 * @Date 2023/11/4
 * @Version 1.0
 **/
//@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({BizException.class})
//    @ResponseBody
    /**不加ResponseBody注解，会出现如下错误
     *ERROR o.a.c.c.C.[.[localhost].[/].[dispatcherServlet] - Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Circular view path [testException]: would dispatch back to the current handler URL [/testException] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)] with root cause
     * javax.servlet.ServletException: Circular view path [testException]: would dispatch back to the current handler URL [/testException] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
     * 	at org.springframework.web.servlet.view.InternalResourceView.prepareForRendering(InternalResourceView.java:210)
     * 	at org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:148)
     * 	at org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:316)
     * 	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1401)
     * 	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1145)
     * 	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1084)
     * 	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
     * 	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
     * 	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
     * 	at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
     * 	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
     * 	at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
     * 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
     * 	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
     * 	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
     * 	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
     * 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)
     * 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)
     * 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:540)
     * 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)
     * 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
     * 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)
     * 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357)
     * 	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:382)
     * 	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
     * 	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:895)
     * 	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1732)
     * 	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
     * 	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
     * 	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
     * 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
     * 	at java.lang.Thread.run(Thread.java:748)
     */
    public Response<Object> handleBizException(HttpServletRequest request, BizException bizException){
        log.warn("{} request fail, errorCode :{}, errorMessage :{}", request.getRequestURI(), bizException.getErrorCode(), bizException.getErrorMessage());
        return Response.fail(bizException);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception){
        //参数错误异常码
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode();
        //获取bindResult
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error -> {
                sb.append(error.getField())
                        .append(" ")
                        .append(error.getDefaultMessage())
                        .append(", 当前值： '")
                        .append(error.getRejectedValue())
                        .append("';");
            });
        });
        String errorMessage = sb.toString();
        log.error("{} request fail, errorCode :{}, errorMessage :{}", request.getRequestURI(), errorCode, errorMessage);
        return Response.fail(errorCode, errorMessage);

    }
}
