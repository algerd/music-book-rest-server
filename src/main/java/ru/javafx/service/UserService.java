package ru.javafx.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import ru.javafx.entity.Authority;
import ru.javafx.entity.User;

@Validated
public interface UserService extends UserDetailsService {

    @Override
    User loadUserByUsername(String username);

    void save(User user);
    
    Authority getAuthority(String authority);
    
    boolean isUserExist(User user);
     
}
