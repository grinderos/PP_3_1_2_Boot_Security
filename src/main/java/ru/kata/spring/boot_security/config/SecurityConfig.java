package ru.kata.spring.boot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.service.LoggedUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoggedUserDetailsService loggedUserDetailsService;

    @Autowired
    public SecurityConfig(LoggedUserDetailsService loggedUserDetailsService) {
        this.loggedUserDetailsService = loggedUserDetailsService;
    }

    //настраивает аутентификацию
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loggedUserDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
