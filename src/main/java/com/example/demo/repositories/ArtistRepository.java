package com.example.demo.repositories;

import com.example.demo.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value= "SELECT * FROM Artist a WHERE a.artist_title = ?1", nativeQuery = true)
    Artist findByArtistName(String artist_title);
        }
