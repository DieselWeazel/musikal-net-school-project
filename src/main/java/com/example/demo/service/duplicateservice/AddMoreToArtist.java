package com.example.demo.service.duplicateservice;

import com.example.demo.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class AddMoreToArtist {

    private final ArtistRepository artistRepository;

    public AddMoreToArtist(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void doYouWantToAddToSameArtist(String artistName) {

    }


}
