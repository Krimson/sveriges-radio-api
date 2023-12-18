package bjes.models;

public enum ChannelEnum {
    P1("132"), P2("163"), P3("164"), P4Dalarna("223");

    String channelId;
    ChannelEnum(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {return this.channelId;}
}
