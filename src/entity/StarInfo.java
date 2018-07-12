package entity;

public class StarInfo {

    int id;
    float distance;
    float flux;

    public StarInfo(int id, float distance, float flux) {
        this.id = id;
        this.distance = distance;
        this.flux = flux;
    }

    public int getId() {
        return id;
    }

    public float getDistance() {
        return distance;
    }

    public float getFlux() {
        return flux;
    }
}
