package ru.kata.spring.boot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
        auth.userDetailsService(loggedUserDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    //конфигурируем сам спринг секьюрити и авторизацию
    protected void configure(HttpSecurity http) throws Exception {
        http
//                //отключаем защиту от межсайтовой подделки запросов
//                .csrf().disable()



                //правила авторизации. Матчеры срабатывают последовательно (как при исключениях), что означает
                //применение при первом подходящем совпадении.
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/register", "/error")
                .permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")

                .and()

                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();

    }
}
