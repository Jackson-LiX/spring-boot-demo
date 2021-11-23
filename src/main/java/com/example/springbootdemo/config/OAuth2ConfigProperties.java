package com.example.springbootdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jackson
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2ConfigProperties {

    private String clientID;
    private String authorizedGrantTypes;
    private String scopes;
    private int accessTokenValidity;
    private String[] resourceAntMatchers;
    private String[] securityAntMatchers;
    private String jwtSigningKey;
}
