package bjes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ArtistsByRecordLabelDTO {
        String recordLabel;
        Set<String> artists;
}
