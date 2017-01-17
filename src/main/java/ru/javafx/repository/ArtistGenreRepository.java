
package ru.javafx.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Artist;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_genres", path = "artist_genres")
public interface ArtistGenreRepository extends PagingAndSortingRepository<ArtistGenre, Long> {
    
    ArtistGenre findByArtistAndGenre(@Param("artist") Artist artist, @Param("genre") Genre genre);
    
    @Query(value = "select * from artist_genre where id_artist = :id_artist and id_genre = :id_genre", nativeQuery = true)
    ArtistGenre findByIdArtistAndIdGenre(
        @Param("id_artist") Long id_artist, 
        @Param("id_genre") Long id_genre);
}
