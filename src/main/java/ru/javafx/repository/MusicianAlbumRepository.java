
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Album;
import ru.javafx.entity.Musician;
import ru.javafx.entity.MusicianAlbum;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_albums", path = "musician_albums")
public interface MusicianAlbumRepository extends PagingAndSortingRepository<MusicianAlbum, Long> {
    
    Long countByMusicianAndAlbum(@Param("musician") Musician musician,  @Param("album") Album album);
    
    MusicianAlbum findByMusicianAndAlbum(@Param("musician") Musician musician,  @Param("album") Album album);
    
    MusicianAlbum findByMusician(@Param("musician") Musician musician);
    
    MusicianAlbum findByAlbum(@Param("album") Album album);
    
}
