
package ru.javafx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javafx.entity.Album;
import ru.javafx.repository.AlbumRepository;
import ru.javafx.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
       
    @Autowired
    private AlbumRepository albumRepository;
    
    @Override
    public Album findAlbum(Long id) {
        return albumRepository.findOne(id);
    }

}
