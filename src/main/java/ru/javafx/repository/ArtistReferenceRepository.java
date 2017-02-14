
package ru.javafx.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.ArtistReference;
import ru.javafx.entity.QArtistReference;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_references", path = "artist_references")
public interface ArtistReferenceRepository extends 
        PagingAndSortingRepository<ArtistReference, Long>,
        QueryDslPredicateExecutor<ArtistReference>,
        QuerydslBinderCustomizer<QArtistReference> {
        
    @Override
    default void customize(QuerydslBindings bindings, QArtistReference artistReference) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());             
        // Alias pathes:
    }
    
    ArtistReference findByName(String name);
    
}
