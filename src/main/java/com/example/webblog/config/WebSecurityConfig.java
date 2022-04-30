package com.example.webblog.config;

import com.example.webblog.security.CustomSuccessHandler;
import com.example.webblog.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/", "/dang-nhap","/trang-chu").permitAll();

//        http.authorizeRequests().antMatchers("/trang-chu").authenticated();
        http.authorizeRequests().antMatchers("/quan-tri/**").access("hasRole('ADMIN')");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/dang-nhap")
                .successHandler(successHandler())
                .failureUrl("/dang-nhap?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/dang-xuat").logoutSuccessUrl("/dang-nhap");
    }


    public CustomSuccessHandler successHandler() {
        return new CustomSuccessHandler();
    }

//    @Bean
//    public SpringSecurityDialect securityDialect() {
//        return new SpringSecurityDialect();
//    }

}
