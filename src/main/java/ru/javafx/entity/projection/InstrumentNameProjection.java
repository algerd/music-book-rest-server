
package ru.javafx.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.javafx.entity.Instrument;

@Projection(name = "get_name", types = {Instrument.class})
public interface InstrumentNameProjection {
    
    String getName();
    
}
