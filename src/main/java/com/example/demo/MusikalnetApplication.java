package com.example.demo;

import com.example.demo.model.Album;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.create.AlbumCreateDTO;
import com.example.demo.service.AbstractMusicService;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusikalnetApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusikalnetApplication.class, args);
  }

}
