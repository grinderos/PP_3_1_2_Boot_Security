package ru.kata.spring.boot_security.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.models.LoggedUser;
import ru.kata.spring.boot_security.service.LoggedUserDetailsService;

@Component
public class LoggedUserValidator implements Validator {

    private final LoggedUserDetailsService loggedUserDetailsService;

    @Autowired
    public LoggedUserValidator(LoggedUserDetailsService loggedUserDetailsService) {
        this.loggedUserDetailsService = loggedUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LoggedUser.class.equals(clazz);
    }


    // надо бы переписать с использованием LoggedUserValidationService где будет возвращаться Optional.
    // Пока что мы будем опираться на метод LoggedUserDetailsService.loadUserByUsername и на выбрасываемое им исключение
    @Override
    public void validate(Object target, Errors errors) {
        LoggedUser loggedUser = (LoggedUser) target;
        try {
            loggedUserDetailsService.loadUserByUsername(loggedUser.getUsername());
        } catch (UsernameNotFoundException e) {
            //так как исключение сработало, значит пользователь не найден и можно завершить регистрацию
            return;
        }
        errors.rejectValue("username","", "Пользователь с таким логином уже существует");
    }
}
