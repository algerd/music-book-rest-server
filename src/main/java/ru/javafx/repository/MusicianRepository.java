
package ru.javafx.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Musician;
import ru.javafx.entity.QMusician;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "musicians", path = "musicians")
public interface MusicianRepository extends 
        PagingAndSortingRepository<Musician, Long>,
        QueryDslPredicateExecutor<Musician>,
        QuerydslBinderCustomizer<QMusician> {
    
    final static Logger LOGGER = LoggerFactory.getLogger(MusicianRepository.class);
          
    @Override
    default void customize(QuerydslBindings bindings, QMusician musician) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());
             
        // Alias pathes:
        bindings.bind(musician.musicianGenres.any().genre.id).as("genre.id").all(new NumberMultiValueBinding<>());
    }
       
}
