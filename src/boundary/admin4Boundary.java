package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class admin4Boundary {
    @FXML
    private TextField nome;
    @FXML
    private TextField satellite;
    @FXML
    private TextField banda;

    public void inserisci() {

        String n = nome.getText();
        String s = satellite.getText();
        String b = banda.getText();

       /* FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }




}
