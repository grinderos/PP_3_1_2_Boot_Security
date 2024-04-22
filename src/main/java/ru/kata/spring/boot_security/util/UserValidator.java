//package ru.kata.spring.boot_security.util;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import ru.kata.spring.boot_security.models.User;
//import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;
//
//@Component
//public class UserValidator implements Validator {
//
//    private final UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Autowired
//    public UserValidator(UserDetailsServiceImpl userDetailsServiceImpl) {
//        this.userDetailsServiceImpl = userDetailsServiceImpl;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.equals(clazz);
//    }
//
//
//    // надо бы переписать с использованием UserValidationService где будет возвращаться Optional.
//    // Пока что мы будем опираться на метод UserDetailsServiceImpl.loadUserByUsername и на выбрасываемое им исключение
//    @Override
//    public void validate(Object target, Errors errors) {
//        User user = (User) target;
//        try {
//            userDetailsServiceImpl.loadUserByUsername(user.getUsername());
//        } catch (UsernameNotFoundException e) {
//            //так как исключение сработало, значит пользователь не найден и можно завершить регистрацию
//            return;
//        }
//        errors.rejectValue("username","", "Пользователь с таким логином уже существует");
//    }
//}
