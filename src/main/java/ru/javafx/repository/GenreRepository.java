
package ru.javafx.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.javafx.entity.Genre;

@Transactional
@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    
    Genre findByName(String name);
    
    @RestResource(path = "by_name", rel = "by_name")
    @Query("select a from Genre a where lower(a.name) like lower(concat(:search, '%')) ")
    Page<Genre> searchByName(
        @Param("search") String search,    
        @Param("pageable") Pageable pageable
    );
    
    @RestResource(path = "by_artist", rel = "by_artist")
    @Query("select genre from Genre genre "
            + "right join genre.artistGenres as joingenres "
            + "where joingenres.artist.id = :id_artist")
    List<Genre> findByArtist(@Param("id_artist") Long id_artist);
    
}
