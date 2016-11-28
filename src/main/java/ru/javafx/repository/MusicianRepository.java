
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Musician;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musicians", path = "musicians")
public interface MusicianRepository extends PagingAndSortingRepository<Musician, Long> {
    
    Musician findByName(String name);
    
}
