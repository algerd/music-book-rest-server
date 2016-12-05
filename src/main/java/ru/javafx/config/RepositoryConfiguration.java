
package ru.javafx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ru.javafx.entity.Album;
import ru.javafx.entity.AlbumGenre;
import ru.javafx.entity.Artist;
import ru.javafx.entity.ArtistGenre;
import ru.javafx.entity.ArtistReference;
import ru.javafx.entity.Authority;
import ru.javafx.entity.Genre;
import ru.javafx.entity.Instrument;
import ru.javafx.entity.Musician;
import ru.javafx.entity.MusicianAlbum;
import ru.javafx.entity.MusicianGenre;
import ru.javafx.entity.MusicianGroup;
import ru.javafx.entity.MusicianInstrument;
import ru.javafx.entity.MusicianSong;
import ru.javafx.entity.Song;
import ru.javafx.entity.SongGenre;
import ru.javafx.entity.User;

// For exposing ID in hal-response
//@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
            User.class,
            Authority.class,
            Album.class,
            AlbumGenre.class,
            Artist.class,
            ArtistGenre.class,
            ArtistReference.class,
            Genre.class,
            Instrument.class,
            Musician.class,
            MusicianAlbum.class,
            MusicianGenre.class,
            MusicianGroup.class,
            MusicianInstrument.class,
            MusicianSong.class,
            Song.class,
            SongGenre.class
        );
    }

}
