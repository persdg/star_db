package control;

import java.util.ArrayList;
import java.lang.Math;

import entity.Star;
import entity.BoundaryPoint;

import persistence.BoundaryRepository;
import persistence.StarRepository;

public class StarController {

    public double[] starsValues = new double[4]; // 1st number of stars, 2nd protostellar %, 3rd prestellar %, 4th unbound %
    private ArrayList<Star> starsList;
    private ArrayList<BoundaryPoint> BoundaryList;
    private float starLat;
    private float starLon;
    private float pointLat;
    private float pointLon;
    private float nextPointLat;
    private float nextPointLon;
    private double formulaValue = 0;



    public double[] starsInBoundary(int id) {


        StarRepository SR = new StarRepository();
        BoundaryRepository BR = new BoundaryRepository();

        starsList = SR.starsInfo(); //list of stars with glon and glat
        BoundaryList = BR.filamentBoundary(id); //list of points (glon,glat) that belongs to a specific filament

        for (Star i : starsList) {

            starLat = i.getLan();
            starLon = i.getGlon();

            for (int j = 0; j<(BoundaryList.size()-1);j++){

                pointLon = BoundaryList.get(j).getX(); //lon current point of boundary
                pointLat = BoundaryList.get(j).getY();//lat current point of boundary

                nextPointLon = BoundaryList.get(j+1).getX();//lon next point of boundary
                nextPointLat = BoundaryList.get(j+1).getY();//lat next point of boundary

                formulaValue = formulaValue + Math.atan(((pointLon-starLon)*(nextPointLat-starLat)-(pointLat-
                        starLat)*(nextPointLon-starLon))/((pointLon-//control formula
                        starLon)*(nextPointLon-starLon)-(pointLat-starLat)*(nextPointLat-starLat)));

            }

            if(Math.abs(formulaValue)>= 0.5729578){

                starsValues[0] ++;
                if(i.getType().equals("PROTOSTELLAR")){ starsValues[1] ++; } //counter of Protostellars
                if(i.getType().equals("PRESTELLAR")){starsValues[2] ++;} //counter of prestellars
                if (i.getType().equals("UNBOUND")){starsValues[3] ++;} //counter of unbounds

            }

            formulaValue = 0;

        }
        //percentages
        starsValues[1] = ((starsValues[1]*100)/starsValues[0]); //protostellar %
        starsValues[2] = ((starsValues[2]*100)/starsValues[0]); //prestellar %
        starsValues[3] = ((starsValues[3]*100)/starsValues[0]); //unbound %

        return starsValues;

    }
}
