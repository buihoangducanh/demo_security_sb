package com.anhbui.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorize ->
                        authorize
                                .requestMatchers("/admin/**").authenticated() // chỉ xác thực các trang /admin
                                .requestMatchers("/user/**").authenticated()
                                .requestMatchers("/products/add-to-cart").authenticated()
                                .requestMatchers("cart/**").authenticated()
                                .requestMatchers("/register").permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login").permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )
                .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}