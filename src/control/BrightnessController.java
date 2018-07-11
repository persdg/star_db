package control;


import entity.Filament;
import java.util.ArrayList;


import exception.EllipticityException;
import exception.NegativeValuesException;
import persistence.FilamentRepository;


public class BrightnessController {

    private double contrast;

    public ArrayList<Filament> brightnessCntrl(int minEllipticity, int maxEllipticity, double brightness) throws NegativeValuesException,EllipticityException { //lista da stampare

        if (brightness < 0) throw new NegativeValuesException(); //stampare a schermo messaggio eccezione
        if(minEllipticity >= maxEllipticity) throw new EllipticityException(); //stampare a schermo messaggio eccezione
        contrast = 1 + (brightness/100);
        FilamentRepository FR = new FilamentRepository();
        return FR.contrastEllipticityFilament(minEllipticity, maxEllipticity, contrast);
        }

}
