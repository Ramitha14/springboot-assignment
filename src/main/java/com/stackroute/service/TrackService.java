package com.stackroute.service;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.muzix.Track;

import java.util.List;

public interface TrackService {

   public Track saveTrack(Track track) throws TrackAlreadyExistsException;

   public void deleteTrack(int id);

   public List<Track> getAllTracks();

    public Track getTrackById(int id);

    public List<Track> getTrackByName(String name);

    public Track updateTrack(Track track) throws TrackNotFoundException;

    public void saveTracksFromApi();

}
