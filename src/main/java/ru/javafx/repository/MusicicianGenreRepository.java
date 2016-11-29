
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.MusicianGenre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musicician_genres", path = "musicician_genres")
public interface MusicicianGenreRepository extends PagingAndSortingRepository<MusicianGenre, Long> {
    
}
