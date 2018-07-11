package control;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Arrays;



import entity.SegmentPoint;
import entity.Star;
import entity.BoundaryPoint;
import entity.Filament;

import exception.NegativeValuesException;
import persistence.BoundaryRepository;
import persistence.FilamentRepository;
import persistence.StarRepository;
import persistence.SegmentRepository;



public class StarController {

    public double[] starsValues = new double[8]; // 1st,2nd,3rd,4th: in boundaries (number of stars,protostellar %,
    //prestellar %, unbound%) 5th,6th,7th,8th: out of boundaries (number of stars,protostellar %, prestellar %,
    //unbound %)
    private double [] starsBoundaryValues = new double[4]; //1st number of stars, 2nd protostellar %, 3rd prestellar %,
    //4th unbound %
    private ArrayList<Star> starsList = new ArrayList<Star>();
    private ArrayList<BoundaryPoint> boundaryList = new ArrayList<BoundaryPoint>();

    private double formulaValue = 0;

    public double[] distances;

    private ArrayList<Star> boundaryStarsList = new ArrayList<Star>();



    public double[] starsInBoundary(int id) throws NegativeValuesException {

        if(id <= 0) throw new NegativeValuesException();

        float starLat;
        float starLon;
        float pointLat;
        float pointLon;
        float nextPointLat;
        float nextPointLon;


        Star star;
        StarRepository SR = new StarRepository();
        BoundaryRepository BR = new BoundaryRepository();

        starsList = SR.starsInfo(); //list of stars with glon and glat
        boundaryList = BR.filamentBoundary(id); //list of points (glon,glat) that belongs to a specific filament

        for (Star i : starsList) {

            starLat = i.getLat();
            starLon = i.getGlon();

            for (int j = 0; j<(boundaryList.size()-1); j++){

                pointLon = boundaryList.get(j).getX(); //lon current point of boundary
                pointLat = boundaryList.get(j).getY();//lat current point of boundary

                nextPointLon = boundaryList.get(j+1).getX();//lon next point of boundary
                nextPointLat = boundaryList.get(j+1).getY();//lat next point of boundary

                formulaValue = formulaValue + Math.atan(((pointLon-starLon)*(nextPointLat-starLat)-(pointLat-
                        starLat)*(nextPointLon-starLon))/((pointLon-//control formula
                        starLon)*(nextPointLon-starLon)-(pointLat-starLat)*(nextPointLat-starLat)));

            }

            if(Math.abs(formulaValue)>= 0.01){


                if(i.getType().equals("PROTOSTELLAR")){ starsBoundaryValues[1] ++; } //counter of Protostellars
                if(i.getType().equals("PRESTELLAR")){starsBoundaryValues[2] ++;} //counter of prestellars
                if (i.getType().equals("UNBOUND")){starsBoundaryValues[3] ++;} //counter of unbounds

                boundaryStarsList.add(i); //add star to the list

            }

            formulaValue = 0;

        }
        starsBoundaryValues[0] = boundaryStarsList.size(); // tot num of stars in boundary
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

    public ArrayList<Star> listOfStarsInBoundary(int id)throws  NegativeValuesException{ //Req 12

        if(id <= 0) throw new NegativeValuesException();

        starsInBoundary(id);

        return boundaryStarsList; //list of stars in the boundary

    }

    public double[] Distance(ArrayList<Star> listOfStars, int id){ //req 12 distanze

        float starGlon,starGlat,segGlat,segGlon;
        double min;
        double dist;

        distances = new double[listOfStars.size()]; //array for the distances of the stars

        SegmentRepository SegR = new SegmentRepository();
        ArrayList<SegmentPoint> segPointList = SegR.skeletonSegmentPointInFil(id);

        for(int k = 0; k < listOfStars.size(); k++){

            starGlon = listOfStars.get(k).getGlon();
            starGlat = listOfStars.get(k).getLat();
            segGlon = segPointList.get(0).getX();
            segGlat = segPointList.get(0).getY();

            min = Math.sqrt(Math.pow((starGlon-segGlon),2)+Math.pow((starGlat-segGlat),2)); //init

            for(int c = 1; c < segPointList.size(); c++){

                segGlon = segPointList.get(c).getX();
                segGlat = segPointList.get(c).getY();

                dist = Math.sqrt(Math.pow((starGlon-segGlon),2)+Math.pow((starGlat-segGlat),2));

                if(dist < min){
                    min = dist;
                }

            }
            distances[k] = min;

        }

        Arrays.sort(distances);

        return distances;

    }


}
