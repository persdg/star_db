package entity;

public class FilamentInfo {

    private double glat;
    private double glon;
    private float height;
    private float width;
    private int count;

    public FilamentInfo(double glat, double glon, float height, float width, int count) {
        this.glat = glat;
        this.glon = glon;
        this.height = height;
        this.width = width;
        this.count = count;
    }

    public double getGlon() { return glon; }

    public double getGlat() {
        return glat;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public int getCount() {
        return count;
    }
}
