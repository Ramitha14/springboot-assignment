package com.stackroute.service;

import com.stackroute.muzix.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    TrackService trackService;
    public SpringContextListener(TrackService trackService)
    {
        this.trackService=trackService;
    }
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        try{
            trackService.saveTrack(new Track(4,"thumhiho","good"));
            trackService.saveTrack(new Track(5,"zindhugi","average"));
            trackService.saveTrack(new Track(6,"pyaar","bad"));
        }catch (Exception e)
        {
            System.out.println("exception handler");
        }
    }
}
