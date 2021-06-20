package com.example.turboaz.security;

import com.example.turboaz.dtos.UserData;
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
            UserData user = new UserData();
            String[] chunks = auth.split("\\.");
            Base64.Decoder decoder = Base64.getDecoder();
            String data = new String(decoder.decode(chunks[1]));
            JsonNode payload = new ObjectMapper().readValue(data,JsonNode.class);
            user.setUsername(payload.get("preferred_name").textValue());
            user.setName(payload.get("name").textValue());
            user.setPhone(payload.get("phone_number").textValue());
            user.setEmail(payload.get("email").textValue());
            user.setRegistrationTime(
                    LocalDateTime.ofEpochSecond(payload.get("created_date").longValue(),
                            0,
                            ZoneOffset.ofHours(4)));
            request.setAttribute("user",user);
        }
        return true;
    }
}
