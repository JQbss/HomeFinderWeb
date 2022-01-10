package com.homefinder.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Order(1000)
public class WebConfig  extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**", "/swagger-ui.html/**");
    }
}
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
//@EnableWebMvc
//public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
//    protected static final String[] ACTUATOR_WHITELIST = {
//            "/actuator/**"
//    };
//
//    protected static final String[] SWAGGER_WHITELIST = {
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html/**",
//    };
//
////    @Value("${client.cors.allowed-origins:*}")
////    private String[] allowedOrigins;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic(); //or anything else, e.g. .oauth2ResourceServer().jwt()
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers(SWAGGER_WHITELIST)
//                .antMatchers(ACTUATOR_WHITELIST);
//    }
//
//
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        AuthenticationProvider authenticationProvider = new AuthenticationProvider() {
////            @Override
////            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
////                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + authentication.getPrincipal().toString().toUpperCase());
////
////                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
////                        authentication.getPrincipal(), authentication.getCredentials(), Collections.singleton(authority));
////                token.setDetails(authentication);
////                return token;
////            }
////
////            @Override
////            public boolean supports(Class<?> aClass) {
////                return true;
////            }
////        };
////
////        return authenticationProvider;
////    }
//}