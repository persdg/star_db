package boundary;

import control.FilamentController;
import entity.FilamentInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class req10Boundary {
    @FXML
    private TextField Base;
    @FXML
    private TextField Altezza;
    @FXML
    private TextField X;
    @FXML
    private TextField Y;


    public void ricercaRegione() {

        String B = Base.getText();
        String A = Altezza.getText();
        String XX = X.getText();
        String YY = Y.getText();

       /* FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/
}
}
