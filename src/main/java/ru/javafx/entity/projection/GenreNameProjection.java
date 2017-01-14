
package ru.javafx.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.javafx.entity.Genre;

@Projection(name = "get_name", types = {Genre.class})
public interface GenreNameProjection {
    
    String getName();
    
}
