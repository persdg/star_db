package control;


import entity.Filament;
import java.util.ArrayList;


import exception.EllipticityException;
import exception.NegativeValuesException;
import persistence.FilamentRepository;


public class BrightnessController {

    private double contrast;
    private int brightFilNum;


    public ArrayList<Filament> brightnessCntrl(int minEllipticity, int maxEllipticity, double brightness) throws NegativeValuesException,EllipticityException { //lista da stampare

        ArrayList<Filament> array;

        if (brightness < 0) {throw new NegativeValuesException();}
        if(minEllipticity >= maxEllipticity) {throw new EllipticityException();}
        contrast = 1 + (brightness/100);
        FilamentRepository FR = new FilamentRepository();
        array = FR.contrastEllipticityFilament(minEllipticity, maxEllipticity, contrast);

        brightFilNum = array.size();

        return array;
        }

    public double brightnessPercentage(){

        double percentage;

        FilamentRepository FR = new FilamentRepository();

        percentage = (brightFilNum*100/FR.filamentCount());

         return percentage;

    }

}
