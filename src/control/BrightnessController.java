package control;

//import view.Brightness;
import entity.Filament;
import java.util.ArrayList;

import entity.FilamentIdName;
import exception.BrightnessException;
import exception.EllipticityException;
import persistence.FilamentRepository;


public class BrightnessController {

    private double contrast;

    public ArrayList<FilamentIdName> brightnessCntrl(int minEllipticity, int maxEllipticity, double brightness) throws BrightnessException,EllipticityException { //lista da stampare

        if (brightness < 0) throw new BrightnessException(); //stampare a schermo messaggio eccezione
        if(minEllipticity >= maxEllipticity) throw new EllipticityException(); //stampare a schermo messaggio eccezione
        contrast = 1 + (brightness/100);
        FilamentRepository FR = new FilamentRepository();
        return FR.contrastEllipticityFilament(minEllipticity, maxEllipticity, contrast);
        }

}
