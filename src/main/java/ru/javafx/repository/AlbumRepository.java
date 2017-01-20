
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
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    
    Album findByName(String name);
    
    @RestResource(path = "by_name_and_rating_and_year", rel = "by_name_and_rating_and_year")
    @Query("select album from Album album where "
            + "lower(album.name) like lower(concat(:search, '%')) "
            + "and album.rating >= :minrating and album.rating <= :maxrating "
            + "and album.year >= :minyear and album.year <= :maxyear")
    Page<Album> searchByNameAndRating(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("minyear") Integer minyear,
        @Param("maxyear") Integer maxyear,       
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_name_and_rating_and_year_and_genre", rel = "by_name_and_rating_and_year_and_genre")
    @Query("select album from Album album "
            + "right join album.albumGenres as joinalbums "
            + "where lower(album.name) like lower(concat(:search, '%')) "
            + "and album.rating >= :minrating and album.rating <= :maxrating "
            + "and album.year >= :minyear and album.year <= :maxyear "
            + "and joinalbums.genre = :genre")
    Page<Album> searchByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,
        @Param("minyear") Integer minyear,
        @Param("maxyear") Integer maxyear,
        @Param("genre") Genre genre, 
        @Param("pageable") Pageable pageable);

    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select albumGenre.album from AlbumGenre albumGenre "
            + "where albumGenre.genre = :genre")
    List<Album> findByGenre(@Param("genre") Genre genre);
}
