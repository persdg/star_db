package entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Satellite {
    private String name;
    private Date firstObservation;
    private Date lastObservation;
    private List<String> agencies;

    public Satellite(String name, String firstObservation, String lastObservation) {
        this.name = name;
        agencies = new ArrayList<>();
        this.firstObservation = getDate(firstObservation);
        this.lastObservation = getDate(lastObservation);
    }
    public String getName() {
        return name;
    }

    public Date getFirstObservation() {
        return firstObservation;
    }

    public Date getLastObservation() {
        return lastObservation;
    }

    public List<String> getAgencies() {
        return agencies;
    }

    public void addAgency(String agency) {
        agencies.add(agency);
    }

    /*public String toString() {
        return "{Name: " + name + " | First Observation: " + firstObservation + " | Last Observation: " + lastObservation + " | Agencies: " + agencies.toString() + " }";
    }*/

    private Date getDate(String date) {
       return Date.valueOf(date);
    }
}
