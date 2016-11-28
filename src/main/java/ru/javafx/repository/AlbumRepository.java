
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Album;

@Transactional
@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    
    Album findByName(String name);
    
}
