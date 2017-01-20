
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
import ru.javafx.entity.Artist;
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    
    Genre findByName(String name);
    
    @RestResource(path = "by_name", rel = "by_name")
    @Query("select genre from Genre genre "
            + "where lower(genre.name) like lower(concat(:search, '%')) ")
    Page<Genre> searchByName(
        @Param("search") String search,    
        @Param("pageable") Pageable pageable
    );

    @RestResource(path = "by_artist", rel = "by_artist")
    @Query("select artistGenre.genre from ArtistGenre artistGenre "
            + "where artistGenre.artist = :artist")
    List<Genre> findByArtist(@Param("artist") Artist artist);
    
    @RestResource(path = "by_album", rel = "by_album")
    @Query("select albumGenre.genre from AlbumGenre albumGenre "
            + "where albumGenre.album = :album")
    List<Genre> findByAlbum(@Param("album") Album album);
    
    @RestResource(path = "exist_by_name", rel = "exist_by_name")
    @Query("select genre from Genre genre "
            + "where trim(lower(genre.name)) = trim(lower(:search))")
    Genre existByName(@Param("search") String search);
    
}
