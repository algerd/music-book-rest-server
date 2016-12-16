
package ru.javafx.service;

import ru.javafx.entity.Genre;

public interface GenreService {
    
    Genre findGenre(String name);
    Genre findGenre(Long id);
    
}
