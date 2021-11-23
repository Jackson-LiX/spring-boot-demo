package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jackson
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {OAuth2ConfigProperties.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2ConfigProperties oAuth2ConfigProperties;

    public WebSecurityConfig(OAuth2ConfigProperties oAuth2ConfigProperties) {
        this.oAuth2ConfigProperties = oAuth2ConfigProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(oAuth2ConfigProperties.getSecurityAntMatchers())
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Support the password grant type
        return super.authenticationManagerBean();
    }
}
