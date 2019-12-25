package com.example.demo.controller.rest;

import com.example.demo.model.Track;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/track/")
public class RestTrackController {

    private final AbstractMusicService<Track, TrackDTO> trackService;

    public RestTrackController(AbstractMusicService<Track, TrackDTO> trackService) {
        this.trackService = trackService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TrackDTO> showAllTracks() {
        return trackService.loadAll();
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public TrackDTO showTrack(Track track) {
        return trackService.findEntity(track);
    }

    @RequestMapping(value = "filter", method = RequestMethod.POST)
    public List<TrackDTO> showFilteredTrack(String filter) {
        return trackService.laodByFilter(filter);
    }

    @RequestMapping(value = "genre", method = RequestMethod.POST)
    public List<TrackDTO> showTracksByGenre(String genre) {
        return trackService.loadByGenre(genre);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TrackDTO createTrack(@RequestBody Track track) {
        System.out.println("got track: " + track.getEntityTitle() + track.getDescription());
        return trackService.createEntity(track);
    }

    @RequestMapping(value = "id", method = RequestMethod.PUT)
    public TrackDTO updateTrack(@PathVariable Long id, @RequestBody Track track) {
        return trackService.updateEntity(id, track);
    }

    @RequestMapping(value = "id", method = RequestMethod.DELETE)
    public TrackDTO deleteTrack(@PathVariable Long id) {
        return trackService.removeEntity(id);
    }

}
