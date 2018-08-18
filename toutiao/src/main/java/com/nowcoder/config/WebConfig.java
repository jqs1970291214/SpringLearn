package com.nowcoder.config;

import com.nowcoder.interceptor.LoginInterceptor;
import com.nowcoder.interceptor.LoginRequiredIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/16 20:11
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private LoginRequiredIntercepter loginRequiredIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
        registry.addInterceptor(loginRequiredIntercepter).addPathPatterns("/username");

        //registry.addInterceptor(loginRequiredIntercepter).addPathPatterns("/username");
        super.addInterceptors(registry);

    }
}
