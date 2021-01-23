/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.config;

import com.example.test.service.UserDetailsServiceImpl;
//import com.example.test.config.SimpleUrlAuthenticationSuccessHandler;
import com.example.test.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * @author Root
 */
// настройка безопасности и авторизации, кому куда можно
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userDetailsService;
    //private UserDetailsService userDetailsService;

    // кодировка пароля
    @Bean
    public BCryptPasswordEncoder passwordEncoderB() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // перенаправление пользователя
     @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new SimpleUrlAuthenticationSuccessHandler();
    }


    public final void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderB());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Страницы не требуют входа в систему
        http.authorizeRequests().antMatchers( "/login", "/logout").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/cabinet/**").access("hasAnyRole('ROLE_USER')");

        // For ADMIN only.
       /* http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");*/

       // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                //.successForwardUrl("/cabinet")
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                //.defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

        
        // Config Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }



    // Token stored in Memory (Of Web Server).
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
