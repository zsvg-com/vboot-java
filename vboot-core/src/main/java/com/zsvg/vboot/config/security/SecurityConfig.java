package com.zsvg.vboot.config.security;

import com.zsvg.vboot.config.dbx.redis.RedisHandler;
import com.zsvg.vboot.config.security.authn.AuthnFailedHandler;
import com.zsvg.vboot.config.security.authz.AuthzDeniedHandler;
import com.zsvg.vboot.config.security.jwt.JwtFilterAdapter;
import com.zsvg.vboot.config.security.jwt.JwtHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.禁用JWT登录无关的相关功能
        http.headers().frameOptions().disable().and()
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //2.跨域处理
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        //3.启用JWT过滤器
        http.apply(securityConfigurerAdapter());

        //4.过滤器异常与授权失败处理
        http.exceptionHandling()
                .authenticationEntryPoint(authnFailedHandler)
                .accessDeniedHandler(authzDeniedHandler);

        //5.授权配置
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers("/gen/**").permitAll()//gen通用的请求直接放行
//                .antMatchers("/sys/**").hasRole("ADMIN")//sys开头的需要管理员权限
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()//放行OPTIONS请求
                .antMatchers("/**").access("@authzHandler.hasPermission(request, authentication)")//其他请求统一认证
//                .antMatchers("/getUserInfo").hasRole("xxx")
                .anyRequest().authenticated());

    }

    //<<-------------------------------2.跨域处理-------------------------------
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        if (environment.acceptsProfiles(Profiles.of("dev"))) {
//            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3100"));
//        } else {
//            configuration.setAllowedOrigins(Collections.singletonList("https://uaa.imooc.com"));
//        }
//        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3100"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("X-Authenticate");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    //>>

    //<<-------------------------------3.启用JWT过滤器-------------------------------
    private final JwtHandler jwtHandler;

    private final RedisHandler redisHandler;

    //JwtFilter 如果通过@Component 注解注入，则web.ignoring().antMatchers 不起作用，还是会被拦截过滤
    private JwtFilterAdapter securityConfigurerAdapter() {
        return new JwtFilterAdapter(jwtHandler, redisHandler);
    }
    //>>

    //<<-------------------------------4.过滤器异常与授权失败处理-------------------------------
    private final AuthzDeniedHandler authzDeniedHandler;//解决认证过的用户访问无权限资源时的异常

    private final AuthnFailedHandler authnFailedHandler;//解决匿名用户访问无权限资源时的异常
    //>>

    //<<-------------------------------X.其他配置-------------------------------
    //配置security忽略的请求与资源
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**","/druid/**","/login", "/pub/**","/index.html","/vben.html","/favicon.ico","/resource/**","/_app.config.js");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(ssLoginProvider);
////        auth.userDetailsService(ssUserDetailsService).passwordEncoder( passwordEncoder());
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    //authn是认证相关的类，authz是授权相关的类


}
