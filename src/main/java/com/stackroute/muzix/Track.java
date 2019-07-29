package com.stackroute.muzix;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {
    @Id
    @ApiModelProperty(notes = "The Track id")
    int trackid;
    @ApiModelProperty(notes = "Name of the track")
    String trackname;
    @ApiModelProperty(notes = "Comments about the track")
    String trackcomment;

}
