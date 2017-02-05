
package ru.javafx.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository extends 
        PagingAndSortingRepository<Artist, Long>, 
        QueryDslPredicateExecutor<Artist>,
        QuerydslBinderCustomizer<QArtist> {
    
    final static Logger LOGGER = LoggerFactory.getLogger(ArtistRepository.class);
    
    @Override
    default void customize(QuerydslBindings bindings, QArtist artist) { 
        /*
            Default pathes:
        */
        //http://localhost:8080/api/artists?name=Metallica  eq("Metallica") 
        //http://localhost:8080/api/artists?name=contains&name=tallica  contains("tallica")
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        // можно пропустить, указан выше
        //bindings.bind(artist.name).all(new StringMultiValueBinding());
        
        //http://localhost:8080/api/artists?rating=5&rating=7   (rating >= 5 and rating <= 7)
        //http://localhost:8080/api/artists?rating=5            (rating == 5)
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());       
        // можно пропустить, указан выше
        //bindings.bind(artist.rating).all(new NumberMultiValueBinding<>());
        
        /*
            Alias pathes:
        */
        //http://localhost:8080/api/artists?albums.any().rating=5&albums.any().rating=10
        //http://localhost:8080/api/artists?album.rating=5&album.rating=10
        //http://localhost:8080/api/artists?album.rating=5 
        bindings.bind(artist.albums.any().rating).as("album.rating").all(new NumberMultiValueBinding<>());       
        bindings.bind(artist.artistGenres.any().genre.id).as("genre.id").all(new NumberMultiValueBinding<>());
        bindings.bind(artist.albums.any().songs.any().id).as("song.id").all(new NumberMultiValueBinding<>());
        
        //examples:
        //bindings.bind(artist.albums.any().songs.any().rating).as("song.rating").all(new NumberMultiValueBinding<>()); 
        //bindings.bind(artist.albums.any().name).as("album.name").all(new StringMultiValueBinding());
        //bindings.bind(artist.artistGenres.any().genre.name).as("genre.name").all(new StringMultiValueBinding());
    }  

    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select artistGenre.artist from ArtistGenre artistGenre "
            + "where artistGenre.genre = :genre")
    List<Artist> findByGenre(@Param("genre") Genre genre);
}
