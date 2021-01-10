package com.korbiak.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AccessControlHeaderFilter> loggingFilter() {
        FilterRegistrationBean<AccessControlHeaderFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AccessControlHeaderFilter());
        registrationBean.addUrlPatterns("/ate-api/*");

        return registrationBean;
    }

}
