package com.example.demo.controller.rest;

import com.example.demo.model.Track;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
