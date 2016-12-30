
package ru.javafx;

public enum PathType {
    
    ARTISTS("artists"),
    ALBUMS("albums");

    private final String path;

    private PathType(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
          
}
