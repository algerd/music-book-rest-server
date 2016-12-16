
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
import ru.javafx.entity.Artist;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.entity.Genre;
import ru.javafx.service.ArtistGenreService;
import ru.javafx.service.ArtistService;
import ru.javafx.service.GenreService;

@RestController
public class ArtistController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ArtistService artistService;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private ArtistGenreService artistGenreService;
    /*
    @RequestMapping(value = "api/artists/{id}/genres", method = RequestMethod.POST)
    public ResponseEntity<Void> saveGenre(@PathVariable Long id, @RequestBody Genre requestGenre) {
        
        Artist artist = artistService.findArtist(id);
        Genre genre = genreService.findGenre(requestGenre.getName());
        //TODO: сделать проверки существования связки artist+genre в таблице artist_genre
        ArtistGenre artistGenre = new ArtistGenre();
        artistGenre.setArtist(artist);
        artistGenre.setGenre(genre);
        artistGenreService.save(artistGenre);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    */
    @RequestMapping(value = "api/artists/{id_artist}/genres/{id_genre}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveGenre(
            @PathVariable("id_artist") Long idArtist,
            @PathVariable("id_genre") Long idGenre) {
        
        Artist artist = artistService.findArtist(idArtist);
        Genre genre = genreService.findGenre(idGenre);    
        
        //TODO: сделать проверки существования связки artist+genre в таблице artist_genre
        ArtistGenre artistGenre = new ArtistGenre();
        artistGenre.setArtist(artist);
        artistGenre.setGenre(genre);
        artistGenreService.save(artistGenre);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "api/artists/{id_artist}/genres/{id_genre}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGenre(
            @PathVariable("id_artist") Long idArtist,
            @PathVariable("id_genre") Long idGenre) {
        
        //ArtistGenre artistGenre = artistGenreService.findByIdArtistAndIdGenre(idArtist, idGenre);
        //artistGenreService.delete(artistGenre);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
