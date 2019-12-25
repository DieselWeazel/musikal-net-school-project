package com.example.demo.repositories;

import com.example.demo.model.Artist;
import com.example.demo.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track getTrackById(Long id);

    List<Track> findByEntityTitleContainingIgnoreCase(String trackName);
}
