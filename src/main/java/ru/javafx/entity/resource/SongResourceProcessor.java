
package ru.javafx.entity.resource;

import java.io.File;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ru.javafx.PathType;
import ru.javafx.entity.Song;
import ru.javafx.utils.ImageUtil;

@Component
public class SongResourceProcessor implements ResourceProcessor<Resource<Song>> {

    @Override
    public Resource<Song> process(Resource<Song> resource) {
        File imageFile = ImageUtil.createImageFile(PathType.SONGS.toString(), resource.getContent().getId(), "jpg");
        if (imageFile.exists()) {
            resource.add(new Link("http://localhost:8080/images/" + PathType.SONGS.toString() + "/" + imageFile.getName(),
                    "get_image"));
        }
        resource.add(new Link(resource.getId().getHref() + "/image", "post_delete_image"));
        return resource;
    }
}
