package com.stackroute.controller;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.muzix.Track;
import com.stackroute.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/v1/trackservice")
@Api(tags = {"Track Controller"})

public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @ApiOperation(value = "Insert/update a track", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<?>saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get a list of all available tracks", response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the track by id", response = ResponseEntity.class)
    @GetMapping("trackservice/{id}")
    public ResponseEntity<?> getTrack( String id){
        try {
            return new ResponseEntity<>(trackService.getTrackById(Integer.parseInt(id)), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @ApiOperation(value = "update the track", response = ResponseEntity.class)
    @PutMapping
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(track);
            responseEntity = new ResponseEntity<>("Successfully updated", HttpStatus.OK);

        }catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete the track of given id", response = ResponseEntity.class)
    @PostMapping("trackservice/{id}")
    public ResponseEntity<?> deleteTrack( String id){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity = new ResponseEntity<>("track deleted", HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search all tracks by name", response = ResponseEntity.class)
    @GetMapping("trackservice/search/{name}")
    public ResponseEntity<?> searchTrack(@PathVariable String name){
        return new ResponseEntity<>(trackService.getTrackByName(name), HttpStatus.OK);

    }

    @GetMapping(value = "/alltrack")
    public ResponseEntity<?> sendUrlData() throws Exception
    {
        return new ResponseEntity<String>(trackService.getUrlData(),HttpStatus.OK);
    }

}
