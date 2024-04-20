//package ru.kata.spring.boot_security.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import ru.kata.spring.boot_security.models.User;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private final User user;
//
//    public UserDetailsImpl() {
//        this.user = new User();
//    }
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()));
////        return user.getRoles();
////        return Collections.EMPTY_LIST;
//        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public User getUser() {
//        return this.user;
//    }
//}
