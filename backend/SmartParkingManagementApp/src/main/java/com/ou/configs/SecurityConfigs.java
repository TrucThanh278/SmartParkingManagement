/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.configs;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author OU
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ou.controllers",
    "com.ou.configs",
    "com.ou.mappers",
    "com.ou.services",
    "com.ou.repositories"
})
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // Remove the ROLE_ prefix
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); 
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
                .antMatchers("/login", "/logout", "/payment/**").permitAll()
                .anyRequest().access("hasRole('ROLE_ADMIN')")
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login?accessDenied")
                .and()
            .csrf().disable();
    }
    
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "djyggobeq",
                        "api_key", "446637785899928",
                        "api_secret", "7EAQ1bjCVEaO2tYArfZbYo58tXo",
                        "secure", true));
        return cloudinary;
    }
}
