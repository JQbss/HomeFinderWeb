package com.homefinder.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//link do dokumentacji http://localhost:8080/swagger-ui/index.html
@Configuration
@EnableWebSecurity
@Order(1000)
public class WebConfig  extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**", "/swagger-ui.html/**");
    }
}
