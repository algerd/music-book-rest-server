
package ru.javafx.repository;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import ru.javafx.entity.Artist;
import ru.javafx.entity.QArtist;

public class ArtistRepositoryImpl extends QueryDslRepositorySupport implements ArtistRepositoryCustom {

    private static final QArtist artist = QArtist.artist;
    
    public ArtistRepositoryImpl() {
        super(Artist.class);
    }

}
