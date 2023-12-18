package bjes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongDTO {
    String title;
    String description;
    String artist;
    //String composer;
    //String albumname;
    //String recordlabel;
    //String lyricist;
    String starttimeutc;
    //String stoptimeutc;
}
