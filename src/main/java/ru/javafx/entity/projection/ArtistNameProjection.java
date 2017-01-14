
package ru.javafx.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.javafx.entity.Artist;

@Projection(name = "get_name", types = {Artist.class})
public interface ArtistNameProjection {
    
    String getName();
    
}
