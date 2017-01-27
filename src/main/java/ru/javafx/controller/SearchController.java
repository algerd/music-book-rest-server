
package ru.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javafx.entity.Artist;
import ru.javafx.repository.ArtistRepository;

@RestController
@RequestMapping(value = "api/")
public class SearchController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ArtistRepository artistRepository;
    
    @RequestMapping(value = "artists", method = RequestMethod.GET)
    public HttpEntity<PagedResources<Artist>> search(
            @RequestParam("search") String search, 
            Pageable pageable,
            PagedResourcesAssembler assembler) {
        
        Page<Artist> artists = artistRepository.findAll(predicate, pageable);
        return new ResponseEntity<>(assembler.toResource(artists), HttpStatus.OK);        
    }

}
