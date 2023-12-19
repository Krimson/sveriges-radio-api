package bjes.mapstruct;

import bjes.models.Song;
import bjes.models.sr.SongResponseDTO;
import bjes.models.SongsByRecordLabelDTO;
import bjes.models.sr.TrackListResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RadioMapper {
    Song mapSongExistingRecordLabel(SongResponseDTO songResponseDTO);

    default SongsByRecordLabelDTO mapSongNewRecordLabel(SongResponseDTO songResponseDTO) {
        SongsByRecordLabelDTO songsByRecordLabelDTO = new SongsByRecordLabelDTO();
        songsByRecordLabelDTO.setRecordLabel(songResponseDTO.getRecordlabel());

        List<Song> songList = new ArrayList<>();
        songList.add(mapSongExistingRecordLabel(songResponseDTO));
        songsByRecordLabelDTO.setSongs(songList);
        return songsByRecordLabelDTO;
    }



    default List<SongsByRecordLabelDTO> mapSongsSortedByRecordLabel(TrackListResponseDTO trackListResponseDTO) {
        List<SongsByRecordLabelDTO> songsByRecordLabelDTOList = new ArrayList<>();
        if(trackListResponseDTO !=  null && trackListResponseDTO.getSong() != null) {
            for(SongResponseDTO songResponseDTO : trackListResponseDTO.getSong().stream().filter(x -> x.getRecordlabel() != null).sorted(Comparator.comparing(SongResponseDTO::getRecordlabel)).toList()) { // sort before
                if(!songsByRecordLabelDTOList.isEmpty() && songsByRecordLabelDTOList.getLast().getRecordLabel() != null && songResponseDTO.getRecordlabel() != null && songsByRecordLabelDTOList.getLast().getRecordLabel().equals(songResponseDTO.getRecordlabel())) {
                    songsByRecordLabelDTOList.getLast().getSongs().add(mapSongExistingRecordLabel(songResponseDTO)); // add song to existing record labels songlist
                }
                else {
                    songsByRecordLabelDTOList.add(mapSongNewRecordLabel(songResponseDTO)); // add new object to return list (new record label)
                }
            }
        }
        return songsByRecordLabelDTOList;
    }
}
