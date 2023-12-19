package bjes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SongsByRecordLabelDTO {
    String recordLabel;
    List<Song> songs = new ArrayList<>();
}
