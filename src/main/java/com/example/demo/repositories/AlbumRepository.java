package com.example.demo.repositories;

import com.example.demo.model.Album;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlbumRepository extends JpaRepository<Album, Long> {

  @Query(value = "SELECT * FROM album a WHERE a.album_title = ?1", nativeQuery = true)
  Album findByAlbumName(String album_title);

  List<Album> findByEntityTitleContainingIgnoreCase(String albumTitle);
}
