package com.example.demo.service;

import com.example.demo.model.MusicEntity;
import com.example.demo.model.dto.EntityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractMusicService<V, X, Y> {
    List<X> loadAll();
    List<X> laodByFilter(String filterValue);
    List<X> loadByGenre(String genre);
    X findEntity(V v);
    X createEntity(Y y);
    X updateEntity(Long id, Y y);
    X removeEntity(Long id);
}
