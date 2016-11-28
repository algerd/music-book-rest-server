
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    
    Genre findByName(String name);
    
}
