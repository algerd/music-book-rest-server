
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Song;

@Transactional
@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    
    Song findByName(String name);
    
}
