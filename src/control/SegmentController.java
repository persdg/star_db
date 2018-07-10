package control;

import entity.BoundaryPoint;

import persistence.BoundaryRepository;
import persistence.SegmentRepository;
import entity.SegmentPoint;
import java.util.ArrayList;

import exception.NegativeValuesException;


public class SegmentController{

    public double[] vertexDistances = new double[2];
    private ArrayList<SegmentPoint> ExtremesList;
    private ArrayList<BoundaryPoint> BoundaryList;

    public double[] extremesDistances(int idbranch){

        BoundaryRepository BR = new BoundaryRepository();




    }



}
