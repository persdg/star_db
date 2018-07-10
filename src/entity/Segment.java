package entity;

import java.util.List;


public class Segment {
    private int id;
    private int idFil;
    private char type;
    private List<SegmentPoint> positions;

    public Segment(int id, int idFil, char type, List<SegmentPoint> positions) {
        this.id = id;
        this.idFil = idFil;
        this.type = type;
        this.positions = positions;
    }

    public int getId() {
        return id;
    }
    public int getFil() {
        return idFil;
    }
    public int getType() {
        return type;
    }
    public List<SegmentPoint> getPositions() { return positions; };
}