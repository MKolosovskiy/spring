package com.shop.configuration;

import com.shop.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/login**", "/register**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler((req, res, auth) -> {
                    res.sendRedirect("/");
                })
                    .failureHandler((req, res, exp) -> {
                    String message;
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        message = "The email and password you’ve entered doesn’t match any account.";
                    } else {
                        message = "Unknown error - " + exp.getMessage();
                    }
                    req.getSession().setAttribute("message", message);
                    res.sendRedirect("/login");
                })
                .and()
                    .logout()
                    .logoutUrl("/signout")
                    .logoutSuccessHandler((req, res, auth) -> {
                    res.sendRedirect("/login");
                })
                .and()
                    .csrf()
                    .disable();
    }
}
