package entity;

public class Star {
    private int id;
    private String name;
    private String type;
    private float glon;
    private float glat;
    private int num_prog;
    private float flux;

    public Star(int id, String name, String type, float glon, float glat, int num_prog, float flux) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.glon = glon;
        this.glat = glat;
        this.num_prog = num_prog;
        this.flux = flux;
    }

    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public float getGlon() {
        return glon;
    }
    public float getLan() {
        return glat;
    }
    public int getNum_Prog() {
        return num_prog;
    }
    public float getFlux() {
        return flux;
    }


}
