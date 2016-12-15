
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.Artist;
import ru.javafx.repository.ArtistRepository;
import ru.javafx.service.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private ArtistRepository artistRepository;
    
    @Override
    public Artist findArtist(Long id) {
        return artistRepository.findOne(id);
    }

}
