package bjes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrackListResponseDTO {
    String copyright;
    List<SongDTO> song;
}
