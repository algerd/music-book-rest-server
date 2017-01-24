
package ru.javafx.repository;

import com.querydsl.core.types.dsl.StringPath;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Artist;
import ru.javafx.entity.Genre;
import ru.javafx.entity.QArtist;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository extends 
        PagingAndSortingRepository<Artist, Long>, 
        QueryDslPredicateExecutor<Artist>, 
        QuerydslBinderCustomizer<QArtist> {
    
    default void customize(QuerydslBindings bindings, QArtist artist) {
        bindings.bind(artist.name).first((path, value) -> {
            System.out.println("path: " + path.getMetadata());
            System.out.println("value: " + value);
            return path.contains(value);
        });        
        bindings.bind(String.class).first((StringPath path, String value) -> {
            System.out.println("path: " + path.getMetadata());
            System.out.println("value: " + value);
            return path.containsIgnoreCase(value);
        });
    }  

    //Artist findByName(String name);
       
    @RestResource(path = "search_artists", rel = "search_artists")
    @Query("select distinct artist from Artist artist "
            + "right join artist.artistGenres as joins "
            + "where lower(artist.name) like lower(concat(:search, '%')) "
            + "and artist.rating >= :minrating and artist.rating <= :maxrating "
            + "and (:selector_genre = 0 or joins.genre = :genre)")
    Page<Artist> searchArtists(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating, 
        @Param("selector_genre") Integer selector_genre,
        @Param("genre") Genre genre, 
        @Param("pageable") Pageable pageable); 
        
    @RestResource(path = "exist_by_name", rel = "exist_by_name")
    @Query("select artist from Artist artist "
            + "where trim(lower(artist.name)) = trim(lower(:search))")
    Artist existByName(@Param("search") String search);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select artistGenre.artist from ArtistGenre artistGenre "
            + "where artistGenre.genre = :genre")
    List<Artist> findByGenre(@Param("genre") Genre genre);
}
