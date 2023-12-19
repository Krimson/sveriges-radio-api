package bjes.models.sr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongResponseDTO {
    String title;
    String description;
    String artist;
    String composer;
    String albumname;
    String recordlabel;
    String lyricist;
    String starttimeutc;
    String stoptimeutc;
}
