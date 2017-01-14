
package ru.javafx.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.AlbumGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "album_genres", path = "album_genres")
public interface AlbumGenreRepository extends PagingAndSortingRepository<AlbumGenre, Long> {

    @Query(value = "select * from album_genre where id_album = :id_album and id_genre = :id_genre", nativeQuery = true)
    AlbumGenre findByIdAlbumAndIdGenre(
        @Param("id_album") Long id_album, 
        @Param("id_genre") Long id_genre);
}
