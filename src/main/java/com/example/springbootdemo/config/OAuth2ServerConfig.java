package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * The config class for the oauth2 server
 *
 * @author Jackson
 */
@Configuration
@EnableAuthorizationServer
@ComponentScan(basePackageClasses = {OAuth2ConfigProperties.class})
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final RedisConnectionFactory redisConnectionFactory;

    private final OAuth2ConfigProperties oAuth2ConfigProperties;

    private final PasswordEncoder passwordEncoder;

    public OAuth2ServerConfig(OAuth2ConfigProperties oAuth2ConfigProperties,
                              AuthenticationManager authenticationManager,
                              RedisConnectionFactory redisConnectionFactory,
                              PasswordEncoder passwordEncoder) {
        this.oAuth2ConfigProperties = oAuth2ConfigProperties;
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomJwtConverter();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(oAuth2ConfigProperties.getJwtSigningKey());
        return converter;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter(), tokenEnhancer()));
        endpoints.tokenStore(jwtTokenStore()).tokenEnhancer(tokenEnhancerChain).reuseRefreshTokens(true);
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds(oAuth2ConfigProperties.getAccessTokenValidity());
        endpoints.tokenServices(tokenServices)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // Allow form authentication
        oauthServer.allowFormAuthenticationForClients().passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(oAuth2ConfigProperties.getClientID())
                .authorizedGrantTypes(oAuth2ConfigProperties.getAuthorizedGrantTypes())
                .scopes(oAuth2ConfigProperties.getScopes())
                .accessTokenValiditySeconds(oAuth2ConfigProperties.getAccessTokenValidity());
    }

}
