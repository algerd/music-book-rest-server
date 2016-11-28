
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistReference;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_references", path = "artist_references")
public interface ArtistReferenceRepository extends PagingAndSortingRepository<ArtistReference, Long> {
    
    ArtistReference findByName(String name);
    
}
