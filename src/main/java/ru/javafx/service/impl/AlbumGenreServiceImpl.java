
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.AlbumGenre;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.repository.AlbumGenreRepository;
import ru.javafx.repository.ArtistGenreRepository;
import ru.javafx.service.AlbumGenreService;

@Service
public class AlbumGenreServiceImpl implements AlbumGenreService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private AlbumGenreRepository albumGenreRepository;
    
    @Override
    public void save(AlbumGenre albumGenre) {
        albumGenreRepository.save(albumGenre);
    }
    
    @Override
     public void delete(AlbumGenre albumGenre) {
        albumGenreRepository.delete(albumGenre);
    }
    
    public AlbumGenre findByIdAlbumAndIdGenre(Long idAlbum, Long idGenre) {
        return albumGenreRepository.findByIdAlbumAndIdGenre(idAlbum, idGenre);
    }
    
}
