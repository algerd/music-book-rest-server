
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_genres", path = "artist_genres")
public interface ArtistGenreRepository extends PagingAndSortingRepository<ArtistGenre, Long> {

}
