package boundary;

import control.FilamentController;
import entity.FilamentInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class req12Boundary {

    @FXML
    private TextField IDFilamento;

    public void ricercaFilamento() {

        String ID = IDFilamento.getText();

       /* FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(ID));
        System.out.println(u);*/



    }


    }

