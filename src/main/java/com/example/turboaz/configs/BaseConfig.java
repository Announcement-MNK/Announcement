package com.example.turboaz.configs;

import com.example.turboaz.security.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseConfig implements WebMvcConfigurer {
    final TokenInterceptor productServiceInterceptor;

    public BaseConfig(TokenInterceptor productServiceInterceptor){
        this.productServiceInterceptor = productServiceInterceptor;
    }
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(productServiceInterceptor);
    }
}
