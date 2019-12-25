package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.create.AlbumCreateDTO;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.TrackRepository;

import java.util.List;

import com.example.demo.service.AbstractMusicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusikalnetApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusikalnetApplication.class, args);
  }

//  @Bean
//  public CommandLineRunner commandLineRunner(AbstractMusicService<Artist, ArtistDTO> artistService,
//                                             AbstractMusicService<Album, AlbumDTO> albumService,
//                                             AbstractMusicService<Track, TrackDTO> trackService) {
//    return args -> {
//      Track track = new Track("Trip to Persist", "Trip track");
//
//      List<Track> trackList = new ArrayList<>();
//      trackList.add(track);
//
//      Artist artist = new Artist("Dr Persist", "Artist persistancedesc");
//      Genre genre = new Genre("Frenchcore", "Persist me Descrip");
//      Album album = new Album("Trip to albums", "Album to persist", trackList, artist, genre);
//
//      List<Album> albumList = new ArrayList<>();
//      albumList.add(album);
//      artist.setArtistAlbumList(albumList);
//      artist.setArtistTrackList(trackList);
//      artist.setGenre(genre);
//      track.setGenre(genre);
//      track.setAlbum(album);
////      System.out.println(artist.toString());
//      System.out.println(artistService.createEntity(artist).toString());
//
//      //      Track track = new Track("TrackTitle", "A track");
//////      trackRepository.save(track);
////      List<Track> trackList = new ArrayList<>();
////      trackList.add(track);
////      Album album = new Album("An album", "An AlbumDescription", trackList);
//////      albumRepository.save(album);
////      Artist artist = new Artist("An Artist", "An Artist Description");
////      List<Album> albumList = new ArrayList<>();
////      albumList.add(album);
////      artist.setArtistAlbumList(albumList);
//////      artistRepository.save(artist);
////
////      artist.setArtistTrackList(trackList);
////
////      Genre genre = new Genre("Genre", "genredesc");
////      genre.setAlbumList(albumList);
////      List<Artist> artistList = new ArrayList<>();
////      artistList.add(artist);
////      genre.setArtistList(artistList);
////      genre.setTrackList(trackList);
////      genreRepository.save(genre);
////////      artistRepository.save(artist);
////
////
////      Track track2= new Track("TrackTitle", "A track");
//////      trackRepository.save(track);
////      List<Track> trackList2 = new ArrayList<>();
////      trackList2.add(track2);
////      Album album2 = new Album("An album", "An AlbumDescription", trackList);
//////      albumRepository.save(album);
////      Artist artist2 = new Artist("An Artist", "An Artist Description");
////      List<Album> albumList2 = new ArrayList<>();
////      albumList2.add(album2);
////      artist2.setArtistAlbumList(albumList2);
//////      artistRepository.save(artist);
////
////      artist2.setArtistTrackList(trackList2);
////
////      Genre genre2 = new Genre("Genre", "genredesc");
////      genre2.setAlbumList(albumList2);
////      List<Artist> artistList2 = new ArrayList<>();
////      artistList2.add(artist2);
////      genre2.setArtistList(artistList2);
////      genre2.setTrackList(trackList2);
////      genreRepository.save(genre2);
////
////      Track track1 = new Track("En sång", "En sång om ingenting");
////      Track track2 = new Track("Lolsång", "En sång om lol");
////      Track track3 = new Track("Derpsong", "En sång om derps");
//////      trackRepository.save(track1);
//////      trackRepository.save(track2);
//////      trackRepository.save(track3);
////
////
////      List<Track> albumTrackList2 = new ArrayList<>();
////      albumTrackList2.add(track1);
////      albumTrackList2.add(track2);
////      albumTrackList2.add(track3);
////      Album album2 = new Album("Ett album", "Album om grejer", albumTrackList2);
//////      track1.setAlbum(album2);
//////      track2.setAlbum(album2);
//////      track3.setAlbum(album2);
//////      albumRepository.save(album2);
////
////      Artist myArtist = new Artist("The artist", "Artist description");
////      List<Album> bunchOfAlbums = new ArrayList<>();
////      bunchOfAlbums.add(album2);
////      myArtist.setArtistAlbumList(bunchOfAlbums);
//////      artistRepository.save(myArtist);
////
////      Genre genre1 = new Genre("Rock", "Rock n roll");
////      genre1.setAlbumList(bunchOfAlbums);
////      List<Artist> artistList = new ArrayList<>();
////      artistList.add(artist);
////      genre1.setArtistList(artistList);
////      genre1.setTrackList(trackList);
////      genreRepository.save(genre1);
////      Genre genre2 = new Genre("Derp", "DerpyDerp");
////      genre2.setAlbumList(albumList);
////      List<Artist> artistList2 = new ArrayList<>();
////      artistList2.add(myArtist);
////      genre2.setArtistList(artistList);
////      genre2.setTrackList(albumTrackList2);
////      genreRepository.save(genre2);
//    };
//  }
//
//  @Bean
//  public CommandLineRunner commandLineRunnerPresent(ArtistRepository artistRepository,
//                                             AlbumRepository albumRepository,
//                                             TrackRepository trackRepository,
//                                             GenreRepository genreRepository) {
//    return args -> {
////      Artist artist = artistRepository.findByArtistName("Dr Persist");
////      System.out.println("Test: " + artist.getEntityTitle());
//
//
//      List<Album> albumFilterList = albumRepository.findByEntityTitleContainingIgnoreCase("Maniac");
//      albumFilterList.forEach(e-> System.out.println("filter result: " + e.getEntityTitle()));
//
//      List<Artist> artistFilterList = artistRepository.findByEntityTitleContainingIgnoreCase("Pa");
//      artistFilterList.forEach(e-> System.out.println("Filtered artist res: " + e.getEntityTitle()));
//
//      List<Track> trackFilterList = trackRepository.findByEntityTitleContainingIgnoreCase("Hardcore");
//
//      trackFilterList.forEach(e-> System.out.println("Filter tracks: " + e.getEntityTitle() + " by: " + e.getArtist().getEntityTitle()));
//    };
//  }
//  @Bean
//  public CommandLineRunner commandLineRunnerPresent2(ArtistRepository artistRepository,
//                                                    AlbumRepository albumRepository,
//                                                    TrackRepository trackRepository,
//                                                    GenreRepository genreRepository) {
//    return args -> {
//      System.out.println("album");
//      albumRepository.findAll().forEach(e -> System.out.println(e.getEntityTitle()));
//    };
//  }
//  @Bean
//  public CommandLineRunner commandLineRunnerPresent3(ArtistRepository artistRepository,
//                                                    AlbumRepository albumRepository,
//                                                    TrackRepository trackRepository,
//                                                    GenreRepository genreRepository) {
//    return args -> {
//      System.out.println("track");
//      trackRepository.findAll().forEach(e -> System.out.println(e.getEntityTitle()));
//    };
//  }
//  @Bean
//  public CommandLineRunner commandLineRunnerPresent4(ArtistRepository artistRepository,
//                                                    AlbumRepository albumRepository,
//                                                    TrackRepository trackRepository,
//                                                    GenreRepository genreRepository) {
//    return args -> {
//      System.out.println("genre");
//      genreRepository.findAll().forEach(e -> System.out.println(e.getEntityTitle()));
//    };
//  }
//
//    @Bean
//    public CommandLineRunner commandLineRunnerPresent5(ArtistRepository artistRepository,
//                                                       AlbumRepository albumRepository,
//                                                       TrackRepository trackRepository,
//                                                       GenreRepository genreRepository) {
//        return args -> {
//            Genre testGenre = genreRepository.getOne(1L);
//            for (Artist a : testGenre.getArtistList()) {
//                System.out.println("Testgenre has artist: " + a.getEntityTitle());
//                for (Album album : a.getArtistAlbumList()) {
//                  System.out.println("This artist has albums: " + album.getEntityTitle());
//                  for (Track track : album.getTrackList()) {
//                    System.out.println("With tracks: " + track.getEntityTitle());
//                  }
//                }
//            }
//        };
//    }

//    @Bean
//  public CommandLineRunner cmdRunnerTestService(AbstractMusicService<Track> trackServiceImpl) {
//    return args -> {
//      List<Track> list = trackServiceImpl.loadAll();
//      for(Track e : list) {
//        System.out.println("Got these tracks. " + e.getEntityTitle());
//      }
//
//      System.out.println("Got track: " + trackServiceImpl.findEntity(1L).getEntityTitle());
//
//    };
//  }
  @Bean
  public CommandLineRunner cmdRunnerTestService2(AbstractMusicService<Album, AlbumDTO, AlbumCreateDTO> albumServiceImpl) {
    return args -> {

      List<AlbumDTO> filterList = albumServiceImpl.laodByFilter("The ");
      filterList.forEach(e-> System.out.println("FILTER SERVICE IMPL: " + e.getName()));

      List<AlbumDTO> fiterGenre = albumServiceImpl.loadByGenre("Frenchcore");
      fiterGenre.forEach(e-> System.out.println("FILTER SERVICE IMPL(frenchcore): " + e.getName()));
    };
  }
}
