package bjes.models.sr;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrackListResponseDTO {
    String copyright;
    List<SongResponseDTO> song;
}
