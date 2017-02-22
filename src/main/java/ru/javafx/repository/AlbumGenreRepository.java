
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Album;
import ru.javafx.entity.AlbumGenre;
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "album_genres", path = "album_genres")
public interface AlbumGenreRepository extends PagingAndSortingRepository<AlbumGenre, Long> {

    AlbumGenre findByAlbumAndGenre(@Param("album") Album album, @Param("genre") Genre genre);
    
    Long countByAlbumAndGenre(@Param("album") Album album, @Param("genre") Genre genre);
    
}
