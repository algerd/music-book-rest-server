
package ru.javafx.service;

import ru.javafx.entity.AlbumGenre;

public interface AlbumGenreService {
    
    void save(AlbumGenre albumGenre);
    void delete(AlbumGenre albumGenre);
    AlbumGenre findByIdAlbumAndIdGenre(Long idAlbum, Long idGenre);     
    
}
