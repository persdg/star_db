package control;

import entity.BoundaryPoint;
import entity.SegmentPoint;

import persistence.BoundaryRepository;
import persistence.SegmentRepository;
import java.util.ArrayList;

import exception.NegativeValuesException;


public class SegmentController{

    public double[] vertexDistances = new double[2];
    private ArrayList<SegmentPoint> extremesList;
    private ArrayList<BoundaryPoint> boundaryList;
    private float boundaryGlat,boundaryGLon,segmentGLat,segmentGlon;
    double min;



    public double[] extremesDistances(int idbranch) throws NegativeValuesException {

        if(idbranch <= 0) throw new NegativeValuesException();

        BoundaryRepository BR = new BoundaryRepository();
        SegmentRepository SR = new SegmentRepository();
        SegmentPoint SP;

        extremesList = SR.segmentExtremes(idbranch);// list of extremes
        boundaryList = BR.segmentBoundary(idbranch);// list of boundary points

        SP = extremesList.get(0); //max num_prog

        vertexDistances[0] = minDistance(boundaryList,SP);

        SP = extremesList.get(1); //min num_prog

        vertexDistances[1] = minDistance(boundaryList,SP);

        return vertexDistances;

    }

    private double minDistance(ArrayList<BoundaryPoint> boundaryList,SegmentPoint SP){

        segmentGlon = SP.getX();
        segmentGLat = SP.getY();
        min = 0;
        double dist;

        boundaryGLon = boundaryList.get(0).getX();
        boundaryGlat = boundaryList.get(0).getY();

        min = Math.sqrt(Math.pow((boundaryGLon-segmentGlon),2)+Math.pow((boundaryGlat-segmentGLat),2)); //init


        for(int i = 1; i<(boundaryList.size()); i++){

            boundaryGLon = boundaryList.get(i).getX();
            boundaryGlat = boundaryList.get(i).getY();

            dist = Math.sqrt(Math.pow((boundaryGLon-segmentGlon),2)+Math.pow((boundaryGlat-segmentGLat),2));

            if(dist < min) {
                min = dist;
            }

        }

        return min;

    }



}
