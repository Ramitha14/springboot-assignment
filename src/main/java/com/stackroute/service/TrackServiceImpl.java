package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.muzix.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackid())){
            throw new TrackAlreadyExistsException("Track already exists");
        }

        return trackRepository.save(track);
    }

    @Override
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {
        return trackRepository.findById(id).orElse(null);
    }

    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.TrackByName(name);
    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        if (!trackRepository.existsById(track.getTrackid())){
            throw new TrackNotFoundException("Track Not Found");
        }
        return trackRepository.save(track);
    }

    //method to get tracks from api and save to database
    @Override
    public void saveTracksFromApi(){
        //RestTemplate gets response from an api
        RestTemplate restTemplate = new RestTemplate();
        //url of Last.fm api
        String fooResourceUrl = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=081010bfa07bd3c5b56ee809b476993a&format=json";
        //store response in a ResponseEntity
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

        //Object Mapper to access the JSON from the response entity
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            //read the response body to get JSON object
            root = mapper.readTree(response.getBody());
            //Store the JSON array in the object to a variable
            ArrayNode arrayNode = (ArrayNode) root.path("tracks").path("track");

            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new Track object and fill it with data using setters
                Track track = new Track();
                track.setTrackname(arrayNode.get(i).path("name").asText());
                track.setTrackcomment(arrayNode.get(i).path("artist").path("name").asText());
                //save the track to database
                trackRepository.save(track);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


