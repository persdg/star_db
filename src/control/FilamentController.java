package control;

import entity.Filament;
import entity.FilamentInfo;
import exception.NegativeValuesException;
import exception.SegmentRangeException;
import persistence.FilamentRepository;
import persistence.SegmentRepository;

import java.util.ArrayList;

public class FilamentController {

    private int id;
    private String name;

    public FilamentInfo filamentId(int id) throws NegativeValuesException{
        if(id < 0){throw new NegativeValuesException();}
        FilamentRepository FR = new FilamentRepository();

        return FR.searchFilaments(id);
    }

    public FilamentInfo filamentName(String name){

        FilamentRepository FR = new FilamentRepository();

        return FR.searchFilaments(name);
    }

    public ArrayList<Filament> filamentNumSegments(int minVal, int maxVal) throws SegmentRangeException,NegativeValuesException {

        if((maxVal - minVal) < 2) {throw new SegmentRangeException();}
        if(maxVal < 0 || minVal < 0) {throw new NegativeValuesException();}

        SegmentRepository SR = new SegmentRepository();

        return SR.SegmentNumber(minVal,maxVal);
    }

}
