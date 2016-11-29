
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.SongGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "song_genres", path = "song_genres")
public interface SongGenreRepository extends PagingAndSortingRepository<SongGenre, Long> {
    
}
