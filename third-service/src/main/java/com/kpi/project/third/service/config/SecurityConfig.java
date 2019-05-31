package com.kpi.project.third.service.config;

import com.kpi.project.third.service.security.jwt.JwtAuthFilter;
import com.kpi.project.third.service.security.jwt.JwtAuthenticationEntryPoint;
import com.kpi.project.third.service.security.jwt.JwtAuthenticationProvider;
import com.kpi.project.third.service.security.jwt.JwtSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

import static com.kpi.project.third.service.keys.Key.URL_API_PATTERN;
import static com.kpi.project.third.service.keys.Key.URL_AUTH_PATTERN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@PropertySource("classpath:strings.properties")
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ROLE_USER = "ROLE_USER";

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;
    private Environment env;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    public JwtAuthFilter authenticationTokenFilter() {
        JwtAuthFilter filter = new JwtAuthFilter(env.getProperty(URL_API_PATTERN));
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEndPoint);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(env.getProperty(URL_AUTH_PATTERN)).permitAll()
                .antMatchers(env.getProperty(URL_API_PATTERN)).authenticated();
    }
}
