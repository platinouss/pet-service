package com.petsitter.kt.auth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AdminAccessFilter> adminAccessFilter() {
        FilterRegistrationBean<AdminAccessFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminAccessFilter());
        registrationBean.addUrlPatterns("/api/v1/admin/code-management/*");
        return registrationBean;
    }
}

