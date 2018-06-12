package entity;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Satellite {
    private String name;
    private Date firstObservation;
    private Date lastObservation;
    private List<String> agencies;

    public Satellite() { agencies = new LinkedList<String>(); }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getFirstObservation() {
        return firstObservation;
    }
    public void setFirstObservation(Date firstObservation) {
        this.firstObservation = firstObservation;
    }
    public Date getLastObservation() {
        return lastObservation;
    }
    public void setLastObservation(Date lastObservation) {
        this.lastObservation = lastObservation;
    }
    public List<String> getAgencies() {
        return agencies;
    }
    public void setAgencies(List<String> agencies) {
        this.agencies = agencies;
    }
    public void addAgency(String agency) {
        this.agencies.add(agency);
    }
    public String toString() {
        return "{Name: " + name + " | First Observation: " + firstObservation + " | Last Observation: " + lastObservation + " | Agencies: " + agencies.toString() + " }";
    }
}
