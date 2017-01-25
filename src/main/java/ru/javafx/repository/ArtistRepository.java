
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
import ru.javafx.entity.Artist;
import ru.javafx.entity.Genre;
import ru.javafx.entity.QArtist;
import ru.javafx.repository.operators.OperatorUtils;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository extends 
        PagingAndSortingRepository<Artist, Long>, 
        QueryDslPredicateExecutor<Artist>, 
        QuerydslBinderCustomizer<QArtist> {
    
    final static Logger logger = LoggerFactory.getLogger(ArtistRepository.class);
    
    @Override
    default void customize(QuerydslBindings bindings, QArtist artist) {
        //http://localhost:8080/api/artists?name=Metallica
        bindings.bind(artist.name).first((path, value) -> {
            logger.info("{}={}", path.toString(), value);
            return path.contains(value);
        }); 

        //http://localhost:8080/api/artists?artist.ratingGt=5 (rating > 5) (??? artist.rating.gt=5)
        OperatorUtils.expressNumberOperators(artist.rating, bindings);
        
        /*
        //http://localhost:8080/api/artists?description=good
        bindings.bind(String.class).first((StringPath path, String value) -> {
            logger.info("{}={}", path.toString(), value);
            return path.containsIgnoreCase(value);         
        });
        */
        /*
        //http://localhost:8080/api/artists?albums.name=453
        bindings.bind(artist.albums.any().name).first((path, value) -> {
            logger.info("{}={}", path.toString(), value);
            return path.eq(value);
        });
        */
        //http://localhost:8080/api/artists?artistGenres.genre.name=Rock
        //http://localhost:8080/api/artists?artistGenres.genre.name=Rock&sort=name,asc
        bindings.bind(artist.artistGenres.any().genre.name).first((path, value) -> {
            logger.info("{}={}", path.toString(), value);
            return path.eq(value);
        });
        
        /*
        String methodName = "";           
        Method method = null;
        try {
            method = path.getClass().getMethod(methodName);
            method.invoke(path, value);
        } 
        catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { 
            e.printStackTrace();
        }           
        */
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
