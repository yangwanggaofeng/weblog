package com.study.weblog.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.weblog.jwt.exception.UsernameOrPasswordNullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName JwtAuthenticationFilter
 * @Description 此过滤器继承了 AbstractAuthenticationProcessingFilter，
 * 用于处理 JWT（JSON Web Token）的用户身份验证过程。
 * 在构造函数中，调用了父类 AbstractAuthenticationProcessingFilter 的构造函数，
 * 通过 AntPathRequestMatcher 指定了处理用户登录的访问地址。这
 * 意味着当请求路径匹配 /login 并且请求方法为 POST 时，该过滤器将被触发。
 * @Author zhang
 * @Date 2023/11/18
 * @Version 1.0
 **/
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 指定用户登录的访问地址
     */
    public JwtAuthenticationFilter(){
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * attemptAuthentication() 方法用于实现用户身份验证的具体逻辑。
     * 首先，我们解析了提交的 JSON 数据，并获取了用户名、密码，校验是否为空，若不为空，则将它们封装到 UsernamePasswordAuthenticationToken 中。
     * 最后，使用 getAuthenticationManager().authenticate() 来触发 Spring Security 的身份验证管理器执行实际的身份验证过程，然后返回身份验证结果。
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        //解析新提交的json数据
        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
        JsonNode usernameNode = jsonNode.get("username");
        JsonNode passwordNode = jsonNode.get("password");
        if(Objects.isNull(usernameNode) || Objects.isNull(passwordNode)
        || StringUtils.isBlank(usernameNode.textValue()) || StringUtils.isBlank(passwordNode.textValue())){
            throw new UsernameOrPasswordNullException("用户名或密码不能为空");
        }
        String username = usernameNode.textValue();
        String password = passwordNode.textValue();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }
}
