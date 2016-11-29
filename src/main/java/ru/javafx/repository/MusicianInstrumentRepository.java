
package ru.javafx.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.MusicianInstrument;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musician_instruments", path = "musician_instruments")
public interface MusicianInstrumentRepository extends PagingAndSortingRepository<MusicianInstrument, Long>{
    
}
