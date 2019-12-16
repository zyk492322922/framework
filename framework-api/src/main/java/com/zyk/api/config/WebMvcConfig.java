package com.zyk.api.config;

import java.io.File;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zyk.api.interceptor.JsonDataFilter;

/**
 * WebMvcConfig
 * @author zhangyk
 * @date 2019/8/8 17:08
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:"+ File.separator+"META-INF"+ File.separator+"resources"+ File.separator);

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:"+ File.separator+"META-INF"+ File.separator+"resources"+ File.separator+"webjars"+ File.separator);

        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:"+ File.separator+"templates"+ File.separator);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");

    }

    @Bean
    FilterRegistrationBean tokenFilter() {
        FilterRegistrationBean filterReg = new FilterRegistrationBean(new JsonDataFilter());
        //优先级
        filterReg.setOrder(70);
        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        filterReg.addUrlPatterns("/*");
        filterReg.addInitParameter("exclusions","/api/swagger-resources/**,/api/null/swagger-resources/**, /api/webjars/**, /api/v2/**, /api/swagger-ui.html/**,/api/doc.html,/api/*,/*");
        return filterReg;
    }
}