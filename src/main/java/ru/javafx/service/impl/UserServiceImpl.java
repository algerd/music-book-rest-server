package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javafx.entity.Authority;
import ru.javafx.entity.User;
import ru.javafx.repository.AuthorityRepository;
import ru.javafx.repository.UserRepository;
import ru.javafx.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User loadUserByUsername(String username) {           
        User user = userRepository.findByUsername(username);
        // make sure the authorities are lazy loaded!!!
        user.getAuthorities().size();       
        logger.info("Authenticated user: " + user);                    
        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
   
    @Override
    public boolean isUserExist(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
    
}
