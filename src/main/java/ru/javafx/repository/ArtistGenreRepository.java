
package ru.javafx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_genres", path = "artist_genres")
public interface ArtistGenreRepository extends PagingAndSortingRepository<ArtistGenre, Long> {
    
    @Query(value = "select * from artist_genre where id_artist = :id_artist and id_genre = :id_genre", nativeQuery = true)
    ArtistGenre findByIdArtistAndIdGenre(
        @Param("id_artist") Long id_artist, 
        @Param("id_genre") Long id_genre);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    Page<ArtistGenre> findByGenre_Id(
        @Param("id") Long id, 
        @Param("pageable") Pageable pageable); 
    
    @RestResource(path = "by_artist", rel = "by_artist")
    Page<ArtistGenre> findByArtist_Id(
        @Param("id") Long id, 
        @Param("pageable") Pageable pageable); 
    
    /*
    @RestResource(path = "search_by_artist", rel = "search_by_artist")
    @Query("select a from ArtistGenre a where "
            + "lower(a.artist.name) like lower(concat(:search, '%')) "
            + "and a.artist.rating >= :minrating and a.artist.rating <= :maxrating "
            + "and a.genre.id = :id")
    Page<ArtistGenre> findByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,    
        @Param("id") Long id, 
        @Param("pageable") Pageable pageable);
    */

}
