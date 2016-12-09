
package ru.javafx.repository;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Artist;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {

    Artist findByName(String name);
    /*
    @RestResource(path = "artists", rel = "artists")
    @Query("select a from Artist a where a.rating >= :minrating and a.rating <= :maxrating")
    Page<Artist> searchArtists(
        @Param("minrating") Integer minrating, 
        @Param("maxrating") Integer maxrating,
        Pageable pageable
    );
    */
    @RestResource(path = "artists", rel = "artists")
    @Query("select a from Artist a where a.rating = :minrating")
    List<Artist> searchArtists(
        @Param("minrating") Integer minrating
    );
    
    //List<Artist> searchArtists(Integer minRating, Integer maxRating);
    //Page<Artist> searchArtists(Integer minRating, Integer maxRating, Pageable pageable);
    
}


/*
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    
    @RestResource(path = "names", rel = "names")
    Artist findByName(String name);
    
}
*/