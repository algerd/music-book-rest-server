
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.Authority;
import ru.javafx.repository.AuthorityRepository;
import ru.javafx.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Override
    public Authority findAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

}
