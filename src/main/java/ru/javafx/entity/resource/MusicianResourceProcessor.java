
package ru.javafx.entity.resource;

import java.io.File;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ru.javafx.PathType;
import ru.javafx.entity.Musician;
import ru.javafx.utils.ImageUtil;

@Component
public class MusicianResourceProcessor implements ResourceProcessor<Resource<Musician>> {

    @Override
    public Resource<Musician> process(Resource<Musician> resource) {
        File imageFile = ImageUtil.createImageFile(PathType.MUSICIANS.toString(), resource.getContent().getId(), "jpg");
        if (imageFile.exists()) {
            resource.add(new Link("http://localhost:8080/images/" + PathType.MUSICIANS.toString() + "/" + imageFile.getName(),
                    "get_image"));
        }
        resource.add(new Link(resource.getId().getHref() + "/image", "post_delete_image"));
        return resource;
    }
}
