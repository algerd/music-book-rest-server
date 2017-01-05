
package ru.javafx;

public enum PathType {
    
    ARTISTS("artists"),
    ALBUMS("albums"),
    GENRES("genres"),
    SONGS("songs"),
    MUSICIANS("musicians"),
    INSTRUMENTS("instruments");

    private final String path;

    private PathType(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
          
}
