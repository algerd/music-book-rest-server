
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Genre;
import ru.javafx.entity.Musician;
import ru.javafx.entity.MusicianGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_genres", path = "musician_genres")
public interface MusicicianGenreRepository extends PagingAndSortingRepository<MusicianGenre, Long> {
    
    MusicianGenre findByMusicianAndGenre(@Param("musician") Musician musician, @Param("genre") Genre genre);
    
    Long countByMusicianAndGenre(@Param("musician") Musician musician, @Param("genre") Genre genre);
    
}
