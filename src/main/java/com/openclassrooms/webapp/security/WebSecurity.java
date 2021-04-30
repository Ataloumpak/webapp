package com.openclassrooms.webapp.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("sergey").password(passwordEncoder.encode("sergey"))
                .roles("manager");

         */
        //auth.eraseCredentials(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/users").hasRole("manager")
                .anyRequest().authenticated()
                .and().formLogin().
                and().httpBasic().disable()
        ;*/
        /*
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable();
                */
        http.authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().disable();


    }
}