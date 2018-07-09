package control;

import exception.NegativeValuesException;
import persistence.FilamentRepository;

import java.util.List;

public class SquareCircleController {

    public List<Integer> circleControl(double glat,double glon,double radius) throws NegativeValuesException{
        if (radius < 0) throw new NegativeValuesException();
        FilamentRepository FR = new FilamentRepository();
        return FR.scanCircle(glat,glon,radius);
    }

    public List<Integer> squareControl(double glat,double glon,double side) throws NegativeValuesException{
        if (side < 0) throw new NegativeValuesException();
        FilamentRepository FR = new FilamentRepository();
        return FR.scanCircle(glat,glon,side);
    }

}
