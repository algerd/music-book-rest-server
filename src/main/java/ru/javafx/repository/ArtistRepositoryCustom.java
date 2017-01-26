
package ru.javafx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javafx.entity.Artist;

@RepositoryRestResource(collectionResourceRel = "custom", path = "custom")
public interface ArtistRepositoryCustom {
   
    @RestResource(path = "custom", rel = "custom")
    Page<Artist> search(
            @Param("search") String search, 
            @Param("pageable") Pageable pageable);
    
}
