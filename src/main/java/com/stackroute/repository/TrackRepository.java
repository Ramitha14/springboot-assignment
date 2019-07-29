package com.stackroute.repository;

import com.stackroute.muzix.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer>
{
    @Query(value="select * from Track where name=?",nativeQuery=true)
    public List<Track> TrackByName(@Param("name") String name);

}
