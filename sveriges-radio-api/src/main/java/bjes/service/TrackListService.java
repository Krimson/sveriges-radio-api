package bjes.service;

import bjes.models.SongDTO;
import bjes.models.TrackListResponseDTO;
import bjes.restclient.SrApiEndpoints;
import bjes.restclient.SverigesRadioRestClient;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackListService {

    @Autowired
    SverigesRadioRestClient restClient;

    public WebTarget target() {
        return restClient.getDefaultClient();
    }

    public List<SongDTO> fetchTrackList(String channelid, String size, String startDate) {
        Response resp = target()
                .path(SrApiEndpoints.CONTEXT_PATH + SrApiEndpoints.GET_PLAYLIST_BY_CHANNEL)
                .queryParam("id", channelid)
                .queryParam("format", "json")
                .queryParam("size", size)
                .queryParam("startdatetime", startDate)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
        TrackListResponseDTO responseWrapper = resp.readEntity(TrackListResponseDTO.class);

        return responseWrapper != null && responseWrapper.getSong() != null ? responseWrapper.getSong() : new ArrayList<>();
    }
}
