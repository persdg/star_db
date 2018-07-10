package entity;

public class SegmentPoint implements Point {

    private float x;
    private float y;
    private int num_prog;
    private float flux;

    public SegmentPoint(float x, float y,int num_prog, float flux) {
        this.x = x;
        this.y = y;
        this.num_prog = num_prog;
        this.flux = flux;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getNum_Prog() {
        return num_prog;
    }
    public float getFlux() {return flux; }
}
