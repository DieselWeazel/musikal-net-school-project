
INSERT INTO genre(description, genre_title) VALUES
('Description: DUMP!', 'DUMP AND BASS');
INSERT INTO artist(description, artist_title, image, genre_id) VALUES
('Artist is a douche', 'DUMP ARTIST', 'img/artist/promo.jpg', 1);
INSERT INTO album(description, album_title, image, artist_id, genre_id) VALUES
('His Album sucks dick', 'DickSucking album', 'img/album/promo-authentic.jpg', 1, 1);
INSERT INTO track(description, track_title, album_id, artist_id, genre_id) VALUES
('track also sucks', '01 dumb track', 1, 1, 1);