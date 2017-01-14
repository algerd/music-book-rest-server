
package ru.javafx.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.javafx.entity.Album;

@Projection(name = "get_name", types = {Album.class})
public interface AlbumNameProjection {
    
    String getName();
    
}
