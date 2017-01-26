
package ru.javafx.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javafx.entity.Artist;
import ru.javafx.entity.QArtist;

@RepositoryRestResource(collectionResourceRel = "custom", path = "custom")
public class ArtistRepositoryImpl extends QueryDslRepositorySupport implements ArtistRepositoryCustom {

    private static final QArtist artist = QArtist.artist;
    
    //????
    //@Autowired
    //ArtistRepository artistRepository;
    
    public ArtistRepositoryImpl() {
        super(Artist.class);
    }
    
    @RestResource(path = "custom", rel = "custom")
    public Page<Artist> search(@Param("search") String search, @Param("pageable") Pageable pageable) {
        return null;
    }

}
