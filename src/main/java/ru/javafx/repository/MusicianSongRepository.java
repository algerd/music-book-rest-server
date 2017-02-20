
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Musician;
import ru.javafx.entity.MusicianSong;
import ru.javafx.entity.Song;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_songs", path = "musician_songs")
public interface MusicianSongRepository extends PagingAndSortingRepository<MusicianSong, Long> {
    
    Long countByMusicianAndSong(@Param("musician") Musician musician,  @Param("song") Song song);
    
    MusicianSong findByMusicianAndSong(@Param("musician") Musician musician,  @Param("song") Song song);
    
    MusicianSong findByMusician(@Param("musician") Musician musician);
    
    MusicianSong findBySong(@Param("song") Song song);
       
}
