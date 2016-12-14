
package ru.javafx.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_genres", path = "artist_genres")
public interface ArtistGenreRepository extends PagingAndSortingRepository<ArtistGenre, Long> {
    /* 
    @Modifying
    @Query(value = "INSERT INTO artist_genre (id_artist, id_genre) VALUES (:id_artist, :id_genre)", nativeQuery = true)
    void insert(
        @Param("id_artist") String id_artist, 
        @Param("id_genre") String id_genre 
    );
    */
}
