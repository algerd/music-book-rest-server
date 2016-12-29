
package ru.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "api/artists/{id_artist}/genres/{id_genre}", method = RequestMethod.POST)
    public ResponseEntity<Void> addGenreToArtist(
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
    public ResponseEntity<Void> deleteGenreFromArtist(
            @PathVariable("id_artist") Long idArtist,
            @PathVariable("id_genre") Long idGenre) {
        
        ArtistGenre artistGenre = artistGenreService.findByIdArtistAndIdGenre(idArtist, idGenre);
        artistGenreService.delete(artistGenre);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "api/artists/{id_artist}/image", method = RequestMethod.POST, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Void> saveArtistImage(
            @PathVariable("id_artist") Long idArtist, 
            HttpEntity<byte[]> requestEntity) {
        
        byte[] payload = requestEntity.getBody();
        System.out.println(payload);
        //InputStream inputStream = servletContext.getResourceAsStream("/images/no_image.jpg");
        //IOUtils.toByteArray(inputStream);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "api/artists/{id_artist}/image", method = RequestMethod.GET, consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Void> getArtistImage(
            @PathVariable("id_artist") Long idArtist, 
            HttpEntity<byte[]> requestEntity) {
        
        byte[] payload = requestEntity.getBody();
        System.out.println(payload);
        //InputStream inputStream = servletContext.getResourceAsStream("/images/no_image.jpg");
        //IOUtils.toByteArray(inputStream);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
