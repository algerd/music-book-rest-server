
package ru.javafx.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.javafx.PathType;
import ru.javafx.entity.Artist;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.entity.Genre;
import ru.javafx.service.ArtistGenreService;
import ru.javafx.service.ArtistService;
import ru.javafx.service.GenreService;
import ru.javafx.utils.ImageUtil;

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
    
    private final List<String> imageFolderList = Arrays.asList(
            PathType.ARTISTS.toString(), 
            PathType.ALBUMS.toString());   
    
    @RequestMapping(value = "api/{folder}/{id}/image", method = RequestMethod.POST, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Void> saveArtistImage(
            @PathVariable("id") Long id,
            @PathVariable("folder") String folder,
            HttpEntity<byte[]> requestEntity,
            @RequestHeader HttpHeaders headers) {                          
        
        if (!imageFolderList.contains(folder)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            
        MediaType mediaType = headers.getContentType();        
        String stringImageFormat = "";
        if (mediaType.equals(MediaType.IMAGE_JPEG)) {
            stringImageFormat = "jpg";
        } else if (mediaType.equals(MediaType.IMAGE_PNG)) {
            stringImageFormat = "png";
        } 
                      
        if (!stringImageFormat.equals("")) {
            File file = ImageUtil.createImageFile(folder, id, stringImageFormat);
            byte[] imageInByte = requestEntity.getBody();
            if(ImageUtil.writeImage(imageInByte, stringImageFormat, file)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }      
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @RequestMapping(value = "api/{folder}/{id}/image", method = RequestMethod.GET, consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getArtistImage(
            @PathVariable("folder") String folder,
            @PathVariable("id") Long id) {
        
        if (!imageFolderList.contains(folder)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
