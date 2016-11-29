
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.MusicianAlbum;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_albums", path = "musician_albums")
public interface MusicianAlbumRepository extends PagingAndSortingRepository<MusicianAlbum, Long> {
    
}
