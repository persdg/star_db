package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Instrument {
    private String name;
    private String satellite;
    private List<Double> bands;


    public Instrument(String name, String satellite) {
        this.name = name;
        this.satellite = satellite;
        bands = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getSatellite() {
        return satellite;
    }

    public void addBand(Double band) {
        bands.add(band);
    }

    public List<Double> getBands() {
        return bands;
    }

    public String toString() {
        return "{Name: " + name + " | Satellite: " + satellite + " }";
    }
}
