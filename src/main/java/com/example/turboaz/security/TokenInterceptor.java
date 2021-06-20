package com.example.turboaz.security;

import com.example.turboaz.dtos.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String auth = request.getHeader("Authorization");
        if(auth != null){
            UserDto user = new UserDto();
            String[] chunks = auth.split("\\.");
            Base64.Decoder decoder = Base64.getDecoder();
            String data = new String(decoder.decode(chunks[1]));
            JsonNode payload = new ObjectMapper().readValue(data,JsonNode.class);
            user.setUsername(payload.get("preferred_name").textValue());
            user.setFullName(payload.get("name").textValue());
            user.setPhone(payload.get("phone_number").textValue());
            request.setAttribute("user",user);
        }
        return true;
    }
}
