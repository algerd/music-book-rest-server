
package ru.javafx.repository;

import java.util.List;
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
    
    @RestResource(path = "by_genre_and_rating_and_name", rel = "by_genre_and_rating_and_name")
    @Query("select a from Artist a "
            + "right join a.artistGenres as joinartists "
            + "where lower(a.name) like lower(concat(:search, '%')) "
            + "and a.rating >= :minrating and a.rating <= :maxrating "
            + "and joinartists.genre.id = :id_genre")
    Page<Artist> searchByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,    
        @Param("id_genre") Long id_genre, 
        @Param("pageable") Pageable pageable);
        
    @RestResource(path = "exist_by_name", rel = "exist_by_name")
    @Query("select a from Artist a where trim(lower(a.name)) = trim(lower(:search))")
    Artist existByName(@Param("search") String search);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select artist from Artist artist "
            + "right join artist.artistGenres as joins "
            + "where joins.genre.id = :id_genre")
    List<Artist> findByGenre(@Param("id_genre") Long id_genre);
        
}
