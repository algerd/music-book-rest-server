
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.repository.ArtistGenreRepository;
import ru.javafx.service.ArtistGenreService;

@Service
public class ArtistGenreServiceImpl implements ArtistGenreService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private ArtistGenreRepository artistGenreRepository;
    
    public void save(ArtistGenre artistGenre) {
        artistGenreRepository.save(artistGenre);
    }

}
