
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.AlbumGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "album_genres", path = "album_genres")
public interface AlbumGenreRepository extends PagingAndSortingRepository<AlbumGenre, Long> {

}
