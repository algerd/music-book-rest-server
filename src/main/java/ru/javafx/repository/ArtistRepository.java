
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
import ru.javafx.entity.Genre;

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
    /*
    @RestResource(path = "by_name_and_rating_and_genre", rel = "by_name_and_rating_and_genre")            
    @Query(value = "select "
            + "artist.id, "
            + "artist.name, "
            + "artist.rating, "
            + "artist.description "
                + "from artist_genre "
                + "left join artist on artist_genre.id_artist = artist.id "
                    + "where "
                        + "lower(a.name) like lower(concat(:search, '%')) "
                        + "and a.rating >= :minrating and a.rating <= :maxrating "
                        + "artist_genre.id_genre = :id_genre",
            nativeQuery = true)
    Page<Artist> searchByNameAndRatingAndGenre(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("id_genre") Integer id_genre,
        @Param("pageable") Pageable pageable
    );
    */
    /*
    @RestResource(path = "by_genre", rel = "by_genre")
    Page<Artist> findByArtistGenres_Genre_Name(
        @Param("name") String name, 
        @Param("pageable") Pageable pageable);  
    */    
    @RestResource(path = "by_name", rel = "by_name")
    @Query("select a from Artist a where trim(lower(a.name)) = trim(lower(:search))")
    Artist existByName(@Param("search") String search);
        
}
