package com.example.demo.controller.rest;

import com.example.demo.model.Track;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.model.dto.create.TrackCreateDTO;
import com.example.demo.service.AbstractMusicService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/track")
public class RestTrackController {

  private final AbstractMusicService<Track, TrackDTO, TrackCreateDTO> trackService;

  public RestTrackController(AbstractMusicService<Track, TrackDTO, TrackCreateDTO> trackService) {
    this.trackService = trackService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<TrackDTO> showAllTracks() {
    return trackService.loadAll();
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public TrackDTO showTrack(Track track) {
    return trackService.findEntity(track);
  }

  @RequestMapping(value = "/filter", method = RequestMethod.POST)
  public List<TrackDTO> showFilteredTrack(String filter) {
    return trackService.laodByFilter(filter);
  }

  @RequestMapping(value = "/genre", method = RequestMethod.POST)
  public List<TrackDTO> showTracksByGenre(String genre) {
    return trackService.loadByGenre(genre);
  }

  @RequestMapping(method = RequestMethod.POST)
  public TrackDTO createTrack(@RequestBody TrackCreateDTO trackCreateDTO) {
    return trackService.createEntity(trackCreateDTO);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public TrackDTO updateTrack(@PathVariable Long id, @RequestBody TrackCreateDTO trackCreateDTO) {
    return trackService.updateEntity(id, trackCreateDTO);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public TrackDTO deleteTrack(@PathVariable Long id) {
    return trackService.removeEntity(id);
  }
}
