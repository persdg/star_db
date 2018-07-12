package entity;

public class Filament {
    private int ID;
    private String name;
    private float totalFlux;
    private float meanFlux;
    private float ellipticity;
    private float contrast;
    private String satellite;
    private String instrument;

    public Filament(int ID, String name, float totalFlux, float meanFlux, float ellipticity, float contrast, String satellite, String instrument) {
        this.ID = ID;
        this.name = name;
        this.totalFlux = totalFlux;
        this.meanFlux = meanFlux;
        this.ellipticity = ellipticity;
        this.contrast = contrast;
        this.satellite = satellite;
        this.instrument = instrument;

    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public float getTotalFlux() {
        return totalFlux;
    }
    public float getMeanFlux() {
        return meanFlux;
    }
    public float getEllipticity() {
        return ellipticity;
    }
    public float getContrast() {
        return contrast;
    }
    public String getSatellite() {
        return satellite;
    }
    public String getInstrument() {
        return instrument;
    }

    public String toString() {
        return "{ IDFil: " + ID + " | Name: " + name + " | Total Flux: " + totalFlux + " | Mean Flux:" + meanFlux + " | Ellipticity: " + ellipticity + " | Contrast: " + contrast + " | Satellite: " + satellite + " | Instrument:" + instrument + " }";
    }
}
