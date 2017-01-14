
package ru.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.javafx.entity.Album;
import ru.javafx.entity.AlbumGenre;
import ru.javafx.entity.Genre;
import ru.javafx.service.AlbumGenreService;
import ru.javafx.service.AlbumService;
import ru.javafx.service.GenreService;

@RestController
@RequestMapping(value = "api/albums/")
public class AlbumController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AlbumService albumService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AlbumGenreService albumGenreService;
    
    @RequestMapping(value = "{id_album}/genres/{id_genre}", method = RequestMethod.POST)
    public ResponseEntity<Void> addGenreToAlbum(
            @PathVariable("id_album") Long idAlbum,
            @PathVariable("id_genre") Long idGenre) {
        
        Album album = albumService.findAlbum(idAlbum);
        Genre genre = genreService.findGenre(idGenre);    
        
        //TODO: сделать проверки существования связки artist+genre в таблице artist_genre
        AlbumGenre albumGenre = new AlbumGenre();
        albumGenre.setAlbum(album);
        albumGenre.setGenre(genre);
        albumGenreService.save(albumGenre);        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id_album}/genres/{id_genre}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGenreFromAlbum(
            @PathVariable("id_album") Long idAlbum,
            @PathVariable("id_genre") Long idGenre) {
        
        AlbumGenre albumGenre = albumGenreService.findByIdAlbumAndIdGenre(idAlbum, idGenre);
        albumGenreService.delete(albumGenre);        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
