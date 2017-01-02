
package ru.javafx.entity.resource;

import java.io.File;
import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ru.javafx.PathType;
import ru.javafx.entity.Artist;
import ru.javafx.utils.ImageUtil;

@Component
public class ArtistResourceProcessor implements ResourceProcessor<Resource<Artist>> {

    @Override
    public Resource<Artist> process(Resource<Artist> resource) {
        File imageFile = ImageUtil.createImageFile(PathType.ARTISTS.toString(), resource.getContent().getId(), "jpg");
        if (imageFile.exists()) {
            resource.add(new Link("http://localhost:8080/images/" + PathType.ARTISTS.toString() + "/" + imageFile.getName(),
                    "get_image"));
        }
        resource.add(new Link(resource.getId().getHref() + "/image", "post_delete_image"));
        return resource;
    }
}
