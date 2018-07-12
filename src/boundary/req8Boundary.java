package boundary;

import control.FilamentController;
import entity.FilamentInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class req8Boundary {
    @FXML
    private TextField raggio;
    @FXML
    private TextField lato;
    @FXML
    private TextField X;
    @FXML
    private TextField Y;


    public void ricercaCerchio() {

        String R = raggio.getText();
        String XX = X.getText();
        String YY = Y.getText();

        /*FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }

    public void ricercaQuadrato() {

        String R = lato.getText();
        String XX = X.getText();
        String YY = Y.getText();

        /*FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }
}
