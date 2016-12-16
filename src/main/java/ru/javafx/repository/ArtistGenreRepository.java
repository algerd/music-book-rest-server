
package ru.javafx.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_genres", path = "artist_genres")
public interface ArtistGenreRepository extends PagingAndSortingRepository<ArtistGenre, Long> {
    /*
    ArtistGenre findByIdArtistAndIdGenre(
        @Param("id_artist") Long id_artist, 
        @Param("id_genre") Long id_genre);
    */
    /*
    List<ArtistGenre> findByIdArtist(@Param("id_artist") Long id_artist);
    
    List<ArtistGenre> findByIdGenre(@Param("id_genre") Long id_genre);
    */
}
