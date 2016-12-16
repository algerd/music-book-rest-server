
package ru.javafx.service;

import ru.javafx.entity.ArtistGenre;

public interface ArtistGenreService {
    
    void save(ArtistGenre artistGenre);
    void delete(ArtistGenre artistGenre);
    //ArtistGenre findByIdArtistAndIdGenre(Long idArtist, Long idGenre);       
}
