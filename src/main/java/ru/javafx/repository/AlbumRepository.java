
package ru.javafx.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.javafx.entity.Album;
import ru.javafx.entity.Artist;
import ru.javafx.entity.Genre;
import ru.javafx.entity.QAlbum;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "albums", path = "albums")
public interface AlbumRepository extends 
        PagingAndSortingRepository<Album, Long>,
        QueryDslPredicateExecutor<Album>,
        QuerydslBinderCustomizer<QAlbum> {
    
    final static Logger LOGGER = LoggerFactory.getLogger(AlbumRepository.class);
    
    @Override
    default void customize(QuerydslBindings bindings, QAlbum album) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());
             
        // Alias pathes:
        //bindings.bind(album.artist.name).all(new StringMultiValueBinding()); // "artist.name" - default
        bindings.bind(album.albumGenres.any().genre.id).as("genre.id").all(new NumberMultiValueBinding<>());
    }
       
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select albumGenre.album from AlbumGenre albumGenre "
            + "where albumGenre.genre = :genre")
    List<Album> findByGenre(@Param("genre") Genre genre);
    
    @RestResource(path = "by_artist", rel = "by_artist")
    List<Album> findByArtist(@Param("artist") Artist artist);
}
