
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
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {

    Artist findByName(String name);
    
    @RestResource(path = "by_rating", rel = "by_rating")
    @Query("select artist from Artist artist "
            + "where artist.rating >= :minrating and artist.rating <= :maxrating")
    Page<Artist> searchByRating(
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_name_and_rating", rel = "by_name_and_rating")
    @Query("select artist from Artist artist where "
            + "lower(artist.name) like lower(concat(:search, '%')) "
            + "and artist.rating >= :minrating and artist.rating <= :maxrating")
    Page<Artist> searchByNameAndRating(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_genre_and_rating_and_name", rel = "by_genre_and_rating_and_name")
    @Query("select artist from Artist artist "
            + "right join artist.artistGenres as joinartists "
            + "where lower(artist.name) like lower(concat(:search, '%')) "
            + "and artist.rating >= :minrating and artist.rating <= :maxrating "
            + "and joinartists.genre = :genre")
    Page<Artist> searchByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,    
        @Param("genre") Genre genre, 
        @Param("pageable") Pageable pageable);
        
    @RestResource(path = "exist_by_name", rel = "exist_by_name")
    @Query("select artist from Artist artist "
            + "where trim(lower(artist.name)) = trim(lower(:search))")
    Artist existByName(@Param("search") String search);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select artistGenre.artist from ArtistGenre artistGenre "
            + "where artistGenre.genre = :genre")
    List<Artist> findByGenre(@Param("genre") Genre genre);
}
