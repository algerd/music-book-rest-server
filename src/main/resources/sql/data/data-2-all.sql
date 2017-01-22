INSERT INTO artist (id, name) VALUES (1, 'Unknown');
INSERT INTO album (id, id_artist, name) VALUES (1, 1, 'Unknown');
INSERT INTO genre (id, name) VALUES (1, 'Unknown');
INSERT INTO instrument (id, name) VALUES (1, 'Unknown');
INSERT INTO artist_genre (id, id_genre, id_artist) VALUES (1, 1, 1);
INSERT INTO album_genre (id, id_genre, id_album) VALUES (1, 1, 1);