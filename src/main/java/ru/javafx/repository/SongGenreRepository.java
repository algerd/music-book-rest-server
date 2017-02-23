
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Genre;
import ru.javafx.entity.Song;
import ru.javafx.entity.SongGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "song_genres", path = "song_genres")
public interface SongGenreRepository extends PagingAndSortingRepository<SongGenre, Long> {
    
    SongGenre findBySongAndGenre(@Param("song") Song song, @Param("genre") Genre genre);
    
    Long countBySongAndGenre(@Param("song") Song song, @Param("genre") Genre genre);
    
}
