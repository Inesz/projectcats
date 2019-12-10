package com.cats.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
//https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/cats/**", "/upload/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/", "/resources/**", "/resources/").permitAll()
                .and()
                .formLogin().failureUrl("/login?error=1").usernameParameter("login").passwordParameter("password").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll()
                .and()
                .csrf().ignoringAntMatchers("/soap/api/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .authoritiesByUsernameQuery(env.getProperty("db.security.authorities.by.username.query"))
                .usersByUsernameQuery(env.getProperty("db.security.users.by.username.query"));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    //https://www.baeldung.com/spring-security-redirect-login
    @Bean
    SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }
}