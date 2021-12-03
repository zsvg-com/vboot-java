package com.zsvg.vboot.config.security.authz;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AuthzDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(403);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        try (ServletOutputStream ous = response.getOutputStream()) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("code", HttpStatus.SC_FORBIDDEN);
            map.put("success", false);
            map.put("message", Optional.ofNullable(request.getAttribute("errorMessage")).orElse("授权失败").toString());
            ous.write(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
        }

    }
}
