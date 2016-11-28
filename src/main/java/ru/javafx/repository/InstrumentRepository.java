
package ru.javafx.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Instrument;

@Transactional
@RepositoryRestResource(collectionResourceRel = "instruments", path = "instruments")
public interface InstrumentRepository {
    
    Instrument findByName(String name);
    
}
