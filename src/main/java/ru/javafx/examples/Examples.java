
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
    /*
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
    */    
}
