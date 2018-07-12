package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class admin2Boundary {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void registra() {

        String u = username.getText();
        String p = password.getText();

       /* FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }
}
