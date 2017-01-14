
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
    
    @Override
    public void save(ArtistGenre artistGenre) {
        artistGenreRepository.save(artistGenre);
    }
    
    @Override
     public void delete(ArtistGenre artistGenre) {
        artistGenreRepository.delete(artistGenre);
    }
    
    @Override
    public ArtistGenre findByIdArtistAndIdGenre(Long idArtist, Long idGenre) {
        return artistGenreRepository.findByIdArtistAndIdGenre(idArtist, idGenre);
    }
   
}
