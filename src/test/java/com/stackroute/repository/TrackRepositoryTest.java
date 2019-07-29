package com.stackroute.repository;

import com.stackroute.muzix.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackid(3);
        track.setTrackname("goodbyes");
        track.setTrackcomment("bad");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackid()).get();
        Assert.assertEquals(3,fetchTrack.getTrackid());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(34,"Mother's daughter","change bass");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackid()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTracks(){
        Track t = new Track(45,"7 rings","");
        Track t1 = new Track(101,"Rainy Season","mismatch");
        trackRepository.save(t);
        trackRepository.save(t1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("7 rings",list.get(0).getTrackname());




    }


}