package control;

import entity.Filament;
import exception.NegativeValuesException;
import persistence.FilamentRepository;

import java.util.List;

public class SquareCircleController {

    public List<Filament> circleControl(float glat, float glon, float radius) throws NegativeValuesException{ //funzione cerchio
        if (radius < 0) throw new NegativeValuesException();
        FilamentRepository FR = new FilamentRepository();
        return FR.scanCircle(glat,glon,radius);
    }

    public List<Filament> squareControl(float glat,float glon,float side) throws NegativeValuesException{ //funzuone quadrato
        if (side < 0) throw new NegativeValuesException();
        FilamentRepository FR = new FilamentRepository();
        return FR.scanSquare(glat,glon,side);
    }

}
