package com.zsvg.vboot.config.security.jwt;

import com.zsvg.vboot.config.dbx.redis.RedisHandler;
import com.zsvg.vboot.config.security.pojo.Zuser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHandler jwtHandler;

    private final RedisHandler redisHandler;


    public JwtFilter(JwtHandler jwtHandler, RedisHandler redisHandler) {
        this.jwtHandler = jwtHandler;
        this.redisHandler = redisHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        System.out.println("拦截的URL:" + request.getRequestURI());
        String token = jwtHandler.getToken(request);
        if (token != null) {
            String uuid = jwtHandler.getClaims(token).getId();
//            System.out.println("过滤器：uuid:" + uuid);
            Zuser zuser = (Zuser) redisHandler.get(uuid);
            if (zuser != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(zuser, null, zuser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前登录状态过期");
                throw new BadCredentialsException("当前登录状态过期");
            }
        }

        //测试用时
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        long start = System.currentTimeMillis();
        log.debug(ip + "--" + uri + "--开始▼...");
        chain.doFilter(request, response);
        long time = (System.currentTimeMillis() - start);
        if (time > 3000) {
            log.warn(ip + "--" +uri + "--结束▲...用时：" + time / 1000 + "秒");
        } else {
            log.debug(ip + "--"+uri + "--结束▲...用时：" + time + "毫秒");
        }
    }


}
