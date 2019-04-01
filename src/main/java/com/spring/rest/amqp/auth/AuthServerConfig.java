package com.spring.rest.amqp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenStore store;

    @Value("${admin}")
    private String client;

    @Value("${secret}")
    private String secret;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory().withClient(client).secret(encoder.encode(secret)).scopes("read", "write", "trust")
                .authorizedGrantTypes("password", "client_credentials", "implicit", "authorization_code",
                        "refresh_token")
                .accessTokenValiditySeconds(50 * 90 * 90 * 90).refreshTokenValiditySeconds(80 * 100 * 100 * 100)
                .resourceIds("amqpID");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer.tokenStore(store).authenticationManager(manager);
    }

}