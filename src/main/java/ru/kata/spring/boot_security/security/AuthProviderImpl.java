//package ru.kata.spring.boot_security.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.service.LoggedUserDetailsService;
//
//import java.util.Collections;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final LoggedUserDetailsService loggedUserDetailsService;
//
//    @Autowired
//    public AuthProviderImpl(LoggedUserDetailsService loggedUserDetailsService) {
//        this.loggedUserDetailsService = loggedUserDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        UserDetails userDetails = loggedUserDetailsService.loadUserByUsername(username);
//        String password = authentication.getCredentials().toString();
//
//        if(!password.equals(userDetails.getPassword())) {
//            throw new BadCredentialsException("неверный пароль");
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDetails, password,
////                userDetails.getAuthorities());
//                Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
////        return false;
//        return true;
//    }
//}


//убираем, потому что реализовали стандартные интерфейсы для работы с принципл и юзерДетаилс