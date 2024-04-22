package ru.kata.spring.boot_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    //настраивает аутентификацию
        protected void configure(AuthenticationManagerBuilder auth, UserDetailsServiceImpl userDetailsServiceImpl) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    //конфигурируем сам спринг секьюрити и авторизацию
    protected void configure(HttpSecurity http) throws Exception {
        http
//                //отключаем защиту от межсайтовой подделки запросов
//                .csrf().disable()


                //правила авторизации. Матчеры срабатывают последовательно (как при исключениях), что означает
                //применение при первом подходящем совпадении.
                .authorizeRequests()
                .antMatchers("/","/start", "/auth/**", "/error").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest()
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and()

//                .formLogin()
//                    .loginPage("/auth/login")
//                  .loginProcessingUrl("/process_login")
//                    .defaultSuccessUrl("/user", true)
//                  .failureUrl("/auth/login?error")
//
//                .and()
//
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login").permitAll();
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(
//                bCryptPasswordEncoder()
//        );
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
