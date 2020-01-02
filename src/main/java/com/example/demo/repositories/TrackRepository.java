package com.example.demo.repositories;

import com.example.demo.model.Track;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
  Track getTrackById(Long id);

  List<Track> findByEntityTitleContainingIgnoreCase(String trackName);
}
