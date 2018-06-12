package entity;

public class SegmentPoint implements Point {

    private float x;
    private float y;

    public SegmentPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
