package control;

import entity.FilamentIdName;
import entity.FilamentInfo;
import exception.SegmentRangeException;
import persistence.FilamentRepository;
import persistence.SegmentRepository;

import java.util.ArrayList;

public class FilamentController {

    private int id;
    private String name;

    public FilamentInfo filamentId(int id){  //parametro: id filamento, return: oggetto filamento con centroide (lat,long) estensione (height,width) e num segmenti
        FilamentRepository FR = new FilamentRepository();
        return FR.searchFilaments(id);
    }

    public FilamentInfo filamentName(String name){ //parametro: name filamento, return: oggetto filamento con centroide (lat,long) estensione (height,width) e num segmenti
        FilamentRepository FR = new FilamentRepository();
        return FR.searchFilaments(name);
    }

    public ArrayList<FilamentIdName> filamentNumSegments(int minVal, int maxVal) throws SegmentRangeException {
        if(maxVal < 0 || minVal < 0 || (maxVal - minVal) < 2) throw new SegmentRangeException();
        SegmentRepository SR = new SegmentRepository();
        return SR.SegmentNumber(minVal,maxVal);
    }

}
