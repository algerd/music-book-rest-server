
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
import ru.javafx.entity.Album;
import ru.javafx.entity.Artist;
import ru.javafx.entity.Genre;
import ru.javafx.entity.QGenre;
import ru.javafx.entity.Song;
import ru.javafx.repository.operators.NumberMultiValueBinding;
import ru.javafx.repository.operators.StringMultiValueBinding;

@Transactional
@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenreRepository extends 
        PagingAndSortingRepository<Genre, Long>,
        QueryDslPredicateExecutor<Genre>,
        QuerydslBinderCustomizer<QGenre> {
    
    @Override
    default void customize(QuerydslBindings bindings, QGenre genre) {
        // Default pathes:
        bindings.bind(String.class).all(new StringMultiValueBinding()); 
        bindings.bind(Integer.class).all(new NumberMultiValueBinding<>());             
        // Alias pathes:
    }

    @RestResource(path = "by_artist", rel = "by_artist")
    @Query("select artistGenre.genre from ArtistGenre artistGenre "
            + "where artistGenre.artist = :artist")
    List<Genre> findByArtist(@Param("artist") Artist artist);
    
    @RestResource(path = "by_album", rel = "by_album")
    @Query("select albumGenre.genre from AlbumGenre albumGenre "
            + "where albumGenre.album = :album")
    List<Genre> findByAlbum(@Param("album") Album album);
    
    @RestResource(path = "by_song", rel = "by_song")
    @Query("select songGenre.genre from SongGenre songGenre "
            + "where songGenre.song = :song")
    List<Genre> findBySong(@Param("song") Song song);
       
}
