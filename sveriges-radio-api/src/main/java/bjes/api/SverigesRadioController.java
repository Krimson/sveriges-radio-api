package bjes.api;

import bjes.mapstruct.RadioMapper;
import bjes.models.ChannelEnum;
import bjes.models.SongsByRecordLabelDTO;
import bjes.models.sr.SongResponseDTO;
import bjes.models.sr.TrackListResponseDTO;
import bjes.service.TrackListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("tracklist")
@Tag(name = "Sveriges Radio API")
public class SverigesRadioController {

    @Autowired
    TrackListService trackListService;

    @Autowired
    RadioMapper radioMapper;

    @GET
    @Path(ApiEndpoints.FETCH_TRACK_LIST)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Fetch the track list from a channel",
            description = "")
    public Response fetchTrackListByChannelId(@PathParam("channel") ChannelEnum channel,
                                              @QueryParam("size") @DefaultValue("5") String size,
                                              @QueryParam("date") @DefaultValue("2023-12-18") String date,
                                              @QueryParam("artists-only") @DefaultValue("false") String artistsOnly) {
        TrackListResponseDTO trackListResponseDTO = trackListService.fetchTrackList(channel.getChannelId(), size, date);

        List<SongsByRecordLabelDTO> songsByRecordLabelDTOList = radioMapper.mapSongsSortedByRecordLabel(trackListResponseDTO);

        if(artistsOnly != null && artistsOnly.equals("true")) {
            return Response.ok(radioMapper.mapArtistsByRecordLabelList(songsByRecordLabelDTOList)).type(MediaType.APPLICATION_JSON).build();
        }

        return Response.ok(songsByRecordLabelDTOList).type(MediaType.APPLICATION_JSON).build();
    }
}
