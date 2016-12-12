
package ru.javafx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
    
    @RestResource(path = "by_rating", rel = "by_rating")
    @Query("select a from Artist a where a.rating >= :minrating and a.rating <= :maxrating")
    Page<Artist> searchByRating(
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_name_and_rating", rel = "by_name_and_rating")
    @Query("select a from Artist a where "
            + "lower(a.name) like lower(concat(:search, '%')) "
            + "and a.rating >= :minrating and a.rating <= :maxrating")
    Page<Artist> searchByNameAndRating(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("pageable") Pageable pageable
    );
        
}
