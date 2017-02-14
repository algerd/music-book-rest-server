
package ru.javafx.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Instrument;
import ru.javafx.entity.Musician;
import ru.javafx.entity.QInstrument;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "instruments", path = "instruments")
public interface InstrumentRepository extends 
        PagingAndSortingRepository<Instrument, Long>,
        QueryDslPredicateExecutor<Instrument>,
        QuerydslBinderCustomizer<QInstrument> {
    
    @Override
    default void customize(QuerydslBindings bindings, QInstrument instrument) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());             
        // Alias pathes:
    }
    
    Instrument findByName(String name);
    
    @RestResource(path = "by_musician", rel = "by_musician")
    @Query("select musicianInstrument.instrument from MusicianInstrument musicianInstrument "
            + "where musicianInstrument.musician = :musician")
    List<Instrument> findByMusician(@Param("musician") Musician musician);
    
}
