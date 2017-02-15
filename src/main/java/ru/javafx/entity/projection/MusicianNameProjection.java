
package ru.javafx.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.javafx.entity.Musician;

@Projection(name = "get_name", types = {Musician.class})
public interface MusicianNameProjection {
    
    String getName();
    
}
