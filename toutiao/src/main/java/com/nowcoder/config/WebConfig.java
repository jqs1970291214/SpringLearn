package com.nowcoder.config;

import com.nowcoder.interceptor.LoginInterceptor;
import com.nowcoder.interceptor.LoginRequiredIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
        registry.addInterceptor(loginRequiredIntercepter);

        //registry.addInterceptor(loginRequiredIntercepter).addPathPatterns("/username");
        super.addInterceptors(registry);
    }

    //增加跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET");
        super.addCorsMappings(registry);
    }
}
