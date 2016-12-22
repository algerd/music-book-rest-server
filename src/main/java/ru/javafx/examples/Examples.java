
package ru.javafx.examples;

public class Examples {
    
    /*
    // пример из spring data jpa
    @RestResource(path = "search_by_artist", rel = "search_by_artist")
    @Query("select a from ArtistGenre a where "
            + "lower(a.artist.name) like lower(concat(:search, '%')) "
            + "and a.artist.rating >= :minrating and a.artist.rating <= :maxrating "
            + "and a.genre.id = :id")
    Page<ArtistGenre> findByGenreAndRatingAndName(
        @Param("search") String search,    
        @Param("minrating") Integer minrating,
        @Param("maxrating") Integer maxrating,    
        @Param("id") Long id, 
        @Param("pageable") Pageable pageable);
    */

}
