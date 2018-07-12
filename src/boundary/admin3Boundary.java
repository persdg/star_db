package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class admin3Boundary {
    @FXML
    private TextField nome;
    @FXML
    private TextField first;
    @FXML
    private TextField last;

    public void inserisci() {

        String n = nome.getText();
        String f = first.getText();
        String l = last.getText();

       /* FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }

}
