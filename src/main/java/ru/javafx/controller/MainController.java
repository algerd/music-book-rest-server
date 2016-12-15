
package ru.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.javafx.entity.User;
import ru.javafx.service.UserService;
import org.springframework.validation.annotation.Validated;
import ru.javafx.service.AuthorityService;

@RestController
public class MainController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthorityService authorityService;
    
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Validated @RequestBody User user, UriComponentsBuilder ucBuilder){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            logger.info("A User with name "+ authentication.getPrincipal() +" is authenticated");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }      
        if (userService.isUserExist(user)) {           
            logger.info("A User with name "+ user.getUsername() +" already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.getAuthorities().add(authorityService.findAuthority("USER"));       
        userService.save(user);       
        logger.info("Creating User: " + user);  
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
