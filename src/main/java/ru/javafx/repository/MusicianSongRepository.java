
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.MusicianSong;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_songs", path = "musician_songs")
public interface MusicianSongRepository extends PagingAndSortingRepository<MusicianSong, Long> {
    
}
