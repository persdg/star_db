package entity;

public class FilamentInfo {

    private float glat;
    private float glon;
    private float height;
    private float width;
    private int count;

    public FilamentInfo(float lat, float lon, float height, float width, int count) {
        this.glat = lat;
        this.glon = lon;
        this.height = height;
        this.width = width;
        this.count = count;
    }

    public float getX() {
        return glat;
    }

    public float getY() {
        return glon;
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
