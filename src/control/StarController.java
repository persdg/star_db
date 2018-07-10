package control;

import java.util.ArrayList;
import java.lang.Math;
import java.util.ArrayList;

import entity.Star;
import entity.BoundaryPoint;
import entity.Filament;

import exception.NegativeValuesException;
import persistence.BoundaryRepository;
import persistence.FilamentRepository;
import persistence.StarRepository;


public class StarController {

    public double[] starsValues = new double[8]; // 1st,2nd,3rd,4th: in boundaries (number of stars,protostellar %,
    //prestellar %, unbound%) 5th,6th,7th,8th: out of boundaries (number of stars,protostellar %, prestellar %,
    //unbound %)
    private double [] starsBoundaryValues = new double[4]; //1st number of stars, 2nd protostellar %, 3rd prestellar %,
    //4th unbound %
    private ArrayList<Star> starsList;
    private ArrayList<BoundaryPoint> BoundaryList;

    private double formulaValue = 0;



    public double[] starsInBoundary(int id) throws NegativeValuesException {

        float starLat;
        float starLon;
        float pointLat;
        float pointLon;
        float nextPointLat;
        float nextPointLon;

        if(id <= 0) throw new NegativeValuesException();

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

                starsBoundaryValues[0] ++;//add 1 to stars in boundary counter
                if(i.getType().equals("PROTOSTELLAR")){ starsBoundaryValues[1] ++; } //counter of Protostellars
                if(i.getType().equals("PRESTELLAR")){starsBoundaryValues[2] ++;} //counter of prestellars
                if (i.getType().equals("UNBOUND")){starsBoundaryValues[3] ++;} //counter of unbounds

            }

            formulaValue = 0;

        }
        //percentages
        starsBoundaryValues[1] = ((starsBoundaryValues[1]*100)/starsBoundaryValues[0]); //protostellar %
        starsBoundaryValues[2] = ((starsBoundaryValues[2]*100)/starsBoundaryValues[0]); //prestellar %
        starsBoundaryValues[3] = ((starsBoundaryValues[3]*100)/starsBoundaryValues[0]); //unbound %

        return starsBoundaryValues;

    }

    public double[] starsInRectControl(float glat,float glon, float basis, float height)throws NegativeValuesException{

        if(basis < 0 || height <0) throw new NegativeValuesException();

        //here we use 3 arrays of double: 1 for the stars in boundaries 1 for the stars in the rect and 1 for
        //return values

        FilamentRepository FR = new FilamentRepository();
        StarRepository SR = new StarRepository();
        int idfil;
        double[] starsInRectValues = new double[3]; //array for the stars in the rect
        double[] starsInBoundaryValues = new double[4]; //array for the stars in one boundary
        double totalStars;

        ArrayList<Filament> Filaments = FR.filamentInRect(glat,glon,basis,height); //list of Filaments contained in rect

        starsInRectValues = SR.starsInRect(glat,glon,basis,height);//number of protostellar,prestellar,unbound in rect
        totalStars = starsInRectValues[0] + starsInRectValues[1] + starsInRectValues[2]; //num of stars in rect

        for(Filament k : Filaments){ //get all the informations about stars in the boundaries in the rect

            idfil = k.getID();

            starsInBoundaryValues = starsInBoundary(idfil);//get the values of stars for one boundary in the rect

            starsValues[0] = starsValues[0] + starsInBoundaryValues[0];//add the number of stars in the boundary to
            //the number of the total stars in the boundaries contained in the rect

            starsValues[1] = starsValues[1] + ((starsInBoundaryValues[0]*starsInBoundaryValues[1])/100);//add the
            //number of protostellars in the boundary to the number of the total protostellars in the boundaries in the
            //rect

            starsValues[2] = starsValues[2] + ((starsInBoundaryValues[0]*starsInBoundaryValues[2])/100);//add the
            //number of prestellars in the boundary to the number of the total prestellars in the boundaries in the
            //rect

            starsValues[3] = starsValues[3] + ((starsInBoundaryValues[0]*starsInBoundaryValues[3])/100);//add the
            //number of unbounds in the boundary to the number of the total unbounds in the boundaries in the rect
        }

        starsValues[4] = totalStars - starsValues[0]; //number of total stars out of boundaries
        starsValues[5] = starsInRectValues[0] - starsValues[1];//number of total protostellars out of boundaris
        starsValues[6] = starsInRectValues[1] - starsValues[2];//number of total prestellars out of boundaris
        starsValues[7] = starsInRectValues[2] - starsValues[3];//number of total unbounds out of boundaris

        //percentages
        starsValues[1] = ((starsValues[1]*100)/starsValues[0]);// %protostellars in boundaries
        starsValues[2] = ((starsValues[2]*100)/starsValues[0]);// %prestellars in boundaries
        starsValues[3] = ((starsValues[3]*100)/starsValues[0]);// %unbounds in boundaries

        starsValues[5] = ((starsValues[5]*100)/starsValues[4]);// %protostellars out of boundaries
        starsValues[6] = ((starsValues[6]*100)/starsValues[4]);// %prestellars out of boundaries
        starsValues[7] = ((starsValues[7]*100)/starsValues[4]);// %unbounds out of boundaries

        starsValues[0] = ((starsValues[0]*100)/totalStars);//% of stars in boundaries
        starsValues[4] = ((starsValues[4]*100)/totalStars);//% of stars out of boundaries

        return starsValues;

    }
}
