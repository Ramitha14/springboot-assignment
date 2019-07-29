package com.stackroute.service;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.Track;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track=new Track();

    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track.setTrackid(5);
        track.setTrackname("Dark nights");
        track.setTrackcomment("average");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

//        verify here verifies that userRepository save method is only called once
//        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);

    }


    @Test
    public void getAllTracks(){

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }





}