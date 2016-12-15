
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.Genre;
import ru.javafx.repository.GenreRepository;
import ru.javafx.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private GenreRepository genreRepository;
    
    @Override
    public Genre findGenre(String name) {
        return genreRepository.findByName(name);
    }

}
