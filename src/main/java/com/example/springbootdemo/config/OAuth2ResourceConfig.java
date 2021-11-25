package com.example.springbootdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * The config class for the oauth2 resource server
 *
 * @author Jackson
 */
@Configuration
@EnableResourceServer
@ComponentScan(basePackageClasses = {OAuth2ConfigProperties.class})
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    private final OAuth2ConfigProperties oAuth2ResourceConfig;

    public OAuth2ResourceConfig(OAuth2ConfigProperties oAuth2ResourceConfig) {
        this.oAuth2ResourceConfig = oAuth2ResourceConfig;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(oAuth2ResourceConfig.getResourceAntMatchers())
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
