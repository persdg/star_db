package entity;

import java.util.List;
import java.util.LinkedList;

public class Instrument {
    private String name;
    private String satellite;
    private List<Integer> bands;


    public Instrument() {
        bands = new LinkedList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSatellite() {
        return satellite;
    }
    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
    public List<Integer> getBands() {
        return bands;
    }
    public void setBands(List<Integer> bands) {
        this.bands = bands;
    }
    public void addBand(int band) {
        this.bands.add(band);
    }
    public String toString() {
        return "{Name: " + name + " | Satellite: " + satellite + " | Bands: " + bands.toString() + " }";
    }
}
