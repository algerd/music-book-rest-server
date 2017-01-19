
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
import ru.javafx.entity.Album;

@Transactional
@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    
    Album findByName(String name);
    
    @RestResource(path = "by_name_and_rating_and_year", rel = "by_name_and_rating_and_year")
    @Query("select a from Album a where "
            + "lower(a.name) like lower(concat(:search, '%')) "
            + "and a.rating >= :minrating and a.rating <= :maxrating "
            + "and a.year >= :minyear and a.year <= :maxyear")
    Page<Album> searchByNameAndRating(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("minyear") Integer minyear,
        @Param("maxyear") Integer maxyear,       
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_name_and_rating_and_year_and_genre", rel = "by_name_and_rating_and_year_and_genre")
    @Query("select a from Album a "
            + "right join a.albumGenres as joinalbums "
            + "where lower(a.name) like lower(concat(:search, '%')) "
            + "and a.rating >= :minrating and a.rating <= :maxrating "
            + "and a.year >= :minyear and a.year <= :maxyear "
            + "and joinalbums.genre.id = :id_genre")
    Page<Album> searchByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("minyear") Integer minyear,
        @Param("maxyear") Integer maxyear,
        @Param("id_genre") Long id_genre, 
        @Param("pageable") Pageable pageable);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select album from Album album "
            + "right join album.albumGenres as joins "
            + "where joins.genre.id = :id_genre")
    List<Album> findByGenre(@Param("id_genre") Long id_genre);
    
}
