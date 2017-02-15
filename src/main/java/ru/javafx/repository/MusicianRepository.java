
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
import ru.javafx.entity.Instrument;
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
        bindings.bind(musician.musicianInstruments.any().instrument.id).as("instrument.id").all(new NumberMultiValueBinding<>());
        bindings.bind(musician.musicianGroups.any().artist.id).as("artist.id").all(new NumberMultiValueBinding<>());
        bindings.bind(musician.musicianAlbums.any().album.id).as("album.id").all(new NumberMultiValueBinding<>());
        bindings.bind(musician.musicianSongs.any().song.id).as("song.id").all(new NumberMultiValueBinding<>());
    }
    
    @RestResource(path = "by_instrument", rel = "by_instrument")
    @Query("select musicianInstrument.musician from MusicianInstrument musicianInstrument "
            + "where musicianInstrument.instrument = :instrument")
    List<Musician> findByInstrument(@Param("instrument") Instrument instrument);
    
    @RestResource(path = "by_genre", rel = "by_genre")
    @Query("select musicianGenre.musician from MusicianGenre musicianGenre "
            + "where musicianGenre.genre = :genre")
    List<Musician> findByGenre(@Param("genre") Genre genre);
    
}
