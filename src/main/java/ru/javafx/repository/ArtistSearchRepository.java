
package ru.javafx.repository;

import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Artist;
import ru.javafx.entity.QArtist;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artist_search", path = "artist_search")
public interface ArtistSearchRepository extends 
        PagingAndSortingRepository<Artist, Long>, 
        QueryDslPredicateExecutor<Artist>, 
        QuerydslBinderCustomizer<QArtist> {
    
    default void customize(QuerydslBindings bindings, QArtist artist) {
        bindings.bind(artist.name).first((path, value) -> path.contains(value));
        
        
        bindings.bind(String.class).first((StringPath path, String value) -> {
            System.out.println("path: " + path.getMetadata());
            System.out.println("value: " + value);
            return path.containsIgnoreCase(value);
        });
    }    

}
