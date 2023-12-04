package com.study.weblog.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * @ClassName JwtTokenHelper
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/18
 * @Version 1.0
 **/
@Component
public class JwtTokenHelper implements InitializingBean {
    /**
     *签发人
     */
    @Value("${jwt.issuser}")
    private String issuser;
    /**
     * token过期时间
     */
    @Value("${jwt.tokenExpireTime}")
    private Long tokenExpireTime;
    /**
     * 秘钥
     */
    private Key key;
    /**
     * JWT 解析
     */
    private JwtParser jwtParser;

    /**
     * 生成一个 Base64 的安全秘钥
     * @return
     */
    private static String generateBase64Key(){
        //生成安全秘钥
        Key  secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        //将密钥进行Base64 编码
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return base64Key;
    }

    public static void main(String[] args) {
        String key = generateBase64Key();
        System.out.println("key + " + key);
    }

    /**
     * 解码配置文件中的Base64编码的key为秘钥
     * @param base64Key
     */
    @Value("${jwt.secret}")
    public void setBase64Key(String base64Key){
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //考虑到不同服务器之间可能存在时间偏差，setAllowedClockSkewSeconds用于设置能够容忍的最大时钟误差
        jwtParser = Jwts.parserBuilder().requireIssuer(issuser)
                .setSigningKey(key).setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 生成token
     * @param username
     * @return
     */
    public String generateToken(String username){
        LocalDateTime now = LocalDateTime.now();
        //Token 失效时间
        LocalDateTime expireTime = now.plusMinutes(tokenExpireTime);
        return Jwts.builder().setSubject(username)
                .setIssuer(issuser)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parseToken(String token){
       try{
           return jwtParser.parseClaimsJws(token);
       }catch (UnsupportedJwtException | MalformedJwtException |SignatureException |IllegalArgumentException e){
           throw  new BadCredentialsException("Token不可用", e);
       }catch (ExpiredJwtException e){
           throw new BadCredentialsException("Token 失效", e);
       }
    }

    /**
     * 校验token是否可用
     * @param token
     */
    public void validateToken(String token){
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析token的用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token){
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
