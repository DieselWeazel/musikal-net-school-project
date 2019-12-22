package com.example.demo.service;

import com.example.demo.model.MusicEntity;
import com.example.demo.model.dto.EntityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractMusicService<V, X> {
    List<X> loadAll();
    X findEntity(V v);
    X createEntity(V v);
    X updateEntity(Long id, V v);
    X removeEntity(Long id);
}
