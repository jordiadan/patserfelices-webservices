package com.example.patserfelices.auth.security;

import com.example.patserfelices.auth.security.jwt.JWTFilter;
import com.example.patserfelices.auth.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(HttpSecurity http) throws Exception {
        JWTFilter jwtFilter = new JWTFilter(this.tokenProvider);
        ((HttpSecurity) ((AuthorizedUrl) ((AuthorizedUrl) ((AuthorizedUrl) http.headers().frameOptions().sameOrigin().and().csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(new String[]{"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger.json"})).permitAll().antMatchers(new String[]{"/signup", "/login", "/public"})).permitAll().anyRequest()).authenticated().and()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
