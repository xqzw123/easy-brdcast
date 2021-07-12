package com.comtom.brdcast.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 */
@Configuration
public class TokenConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RequestHandlerInterceptor requestHandlerInterceptor() {
        return new RequestHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(requestHandlerInterceptor());
        registration.addPathPatterns("/safeRest/**");                //拦截路径
    }

}
