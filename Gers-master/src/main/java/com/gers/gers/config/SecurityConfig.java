package com.gers.gers.config;

import com.gers.gers.service.UserInfoUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/register",
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/scripts/**",
            "/",
            "/login",
            "/resources/**",
            "/Admin",
            "/registerUser"

    };
    @Bean
    public UserDetailsService userDetailsService(){
       return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
               .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
               .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/success", true)
               .failureUrl("/login?error=true")
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/")
               .invalidateHttpSession(true)
               .clearAuthentication(true);

       return http.build();


    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/resources/**", "/static/**", "/img/**", "/lib/**", "/favicon.ico");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
