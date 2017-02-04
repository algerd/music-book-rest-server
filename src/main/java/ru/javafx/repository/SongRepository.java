
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
import ru.javafx.entity.Genre;
import ru.javafx.entity.QSong;
import ru.javafx.entity.Song;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository extends 
        PagingAndSortingRepository<Song, Long>,
        QueryDslPredicateExecutor<Song>,
        QuerydslBinderCustomizer<QSong> {
    
    final static Logger LOGGER = LoggerFactory.getLogger(SongRepository.class);
    
    @Override
    default void customize(QuerydslBindings bindings, QSong song) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());
             
        // Alias pathes:
        //bindings.bind(song.album.name).all(new StringMultiValueBinding()); // "album.name" - default
        bindings.bind(song.album.artist.name).as("artist.name").all(new StringMultiValueBinding());
        bindings.bind(song.songGenres.any().genre.id).as("genre.id").all(new NumberMultiValueBinding<>());
    }

    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select songGenre.song from SongGenre songGenre "
            + "where songGenre.genre = :genre")
    List<Song> findByGenre(@Param("genre") Genre genre);
    
}
