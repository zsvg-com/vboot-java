package com.zsvg.vboot.config.security.jwt;

import cn.hutool.core.util.IdUtil;
import com.zsvg.vboot.config.AppConfig;
import com.zsvg.vboot.config.dbx.redis.RedisHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.ArrayList;

@Component
public class JwtHandler implements InitializingBean {

    private final Key key; // 用于签名 Access Token
    private final Key refreshKey; // 用于签名 Refresh Token
    private final AppConfig appConfig;
    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;
    private final RedisHandler redisHandler;

    public JwtHandler(AppConfig appConfig, RedisHandler redisHandler) {
        this.appConfig = appConfig;
        this.redisHandler = redisHandler;
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(appConfig.getJwt().getAkey()));
        refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(appConfig.getJwt().getRkey()));
    }

    @Override
    public void afterPropertiesSet() {
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        jwtBuilder = Jwts.builder().signWith(key, SignatureAlgorithm.HS512);
    }

    //创建Token 设置永不过期，Token 的时间有效性转到Redis 维护
    public String createToken(Authentication authentication) {
        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .claim("user", authentication.getName())
                .setSubject(authentication.getName())
                .compact();
    }

    public String createTokenByUuid(String uuid) {
//        System.out.println("uuidz:" + uuid);
        return jwtBuilder
                .setId(uuid)
                .claim("username", uuid)
                .setSubject(uuid)
                .compact();
    }


    //依据Token 获取鉴权信息
    Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        User principal = new User(claims.getSubject(), "******", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    public Claims getClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    //需要检查的token
//    public void checkRenewal(String token) {
//        // 判断是否续期token,计算token的过期时间
//        long time = redisHandler.getExpire("online" + token) * 1000;
//        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
//        // 判断当前时间与过期时间的时间差
//        long differ = expireDate.getTime() - System.currentTimeMillis();
//        // 如果在续期检查的范围内，则续期
//        if (differ <= appConfig.getJwt().getDetect()) {
//            long renew = time + appConfig.getJwt().getRenew();
//            redisHandler.expire(appConfig.getJwt().getOnlineKey() + token, renew, TimeUnit.MILLISECONDS);
//        }
//    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(appConfig.getJwt().getHeader());
        if (requestHeader != null && requestHeader.startsWith(appConfig.getJwt().getPrefix())) {
            return requestHeader.substring(7);
        }
        return null;
    }

}
