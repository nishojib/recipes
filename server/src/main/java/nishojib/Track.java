package nishojib;

public class Track {
    private int trackId;
    private String name;
    private String composer;
    private int milliseconds;
    private int bytes;
    private double unitPrice;

    public Track(int trackId, String name, String composer, int milliseconds, int bytes, double unitPrice) {
        this.trackId = trackId;
        this.name = name;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }
}
