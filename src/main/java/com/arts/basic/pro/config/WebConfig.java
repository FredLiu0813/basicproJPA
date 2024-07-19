package com.arts.basic.pro.config;

import com.arts.basic.pro.filter.RateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 * @date 2024/5/29
 * @apiNote
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截需要限流的请求路径
        registry.addInterceptor(new RateLimitInterceptor()).addPathPatterns("/api/**");
    }
}
