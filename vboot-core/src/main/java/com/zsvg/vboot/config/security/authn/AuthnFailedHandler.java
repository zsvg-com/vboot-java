package com.zsvg.vboot.config.security.authn;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AuthnFailedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        if (!response.isCommitted()) {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setStatus(200);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try (ServletOutputStream ous = response.getOutputStream()) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("code", HttpStatus.SC_UNAUTHORIZED);
//                map.put("code", HttpStatus.SC_OK);
                map.put("success", false);
                System.out.println(e.getMessage());
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException==null?"Unauthorized":authException.getMessage());

                map.put("message", Optional.ofNullable(request.getAttribute("errorMessage")).orElse("认证失败").toString());
                ous.write(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
