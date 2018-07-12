package boundary;

import entity.FilamentInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import persistence.FilamentRepository;


public class sampleBoundary {

    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private ComboBox<String > combo3;


    public void initialize() {

        ObservableList<String> valor =
                FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10");

        combo1.setItems(valor);

        ObservableList<String> valo =
                FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10");

        combo2.setItems(valo);

        ObservableList<String> val =
                FXCollections.observableArrayList("10","20","30","40","50","60","70","80","90","100");

        combo3.setItems(val);


    }

    public void ricercaContrastoEllitticita() {

        String contenutoCombo1 = combo1.getSelectionModel().getSelectedItem();
        String contenutoCombo2 = combo2.getSelectionModel().getSelectedItem();
        String contenutoCombo3 = combo3.getSelectionModel().getSelectedItem();

       /* FilamentRepository FR = new FilamentRepository() ;

        FilamentInfo u = FR.searchFilaments(Integer.parseInt(ID));
        System.out.println(u);

    }*/


}
}

