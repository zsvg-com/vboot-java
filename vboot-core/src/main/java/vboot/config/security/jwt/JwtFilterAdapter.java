package vboot.config.security.jwt;

import lombok.RequiredArgsConstructor;
import vboot.config.dbx.redis.RedisHandler;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtFilterAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtHandler jwtHandler;
    private final RedisHandler redisHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtHandler, redisHandler);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
