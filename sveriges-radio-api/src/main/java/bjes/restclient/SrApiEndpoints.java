package bjes.restclient;

public interface SrApiEndpoints {
    String CONTEXT_PATH = "api/v2";
    String GET_PLAYLIST_BY_CHANNEL = "/playlists/getplaylistbychannelid";
    // example: curl -X GET "https://api.sr.se/api/v2/playlists/getplaylistbychannelid?id=132&format=json&size=3&startdatetime=2014-09-01"

}
