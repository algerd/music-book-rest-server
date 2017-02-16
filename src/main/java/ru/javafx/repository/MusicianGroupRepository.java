
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Artist;
import ru.javafx.entity.Musician;
import ru.javafx.entity.MusicianGroup;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_groups", path = "musician_groups")
public interface MusicianGroupRepository extends PagingAndSortingRepository<MusicianGroup, Long> {
    
    MusicianGroup findByMusicianAndArtist(@Param("musician") Musician musician,  @Param("artist") Artist artist);
    
    MusicianGroup findByMusician(@Param("musician") Musician musician);
    
    MusicianGroup findByArtist(@Param("artist") Artist artist);
    
}
