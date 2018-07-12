package boundary;

import control.BrightnessController;
import entity.Filament;
import entity.FilamentInfo;
import exception.EllipticityException;
import exception.NegativeValuesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.FilamentRepository;

import java.util.ArrayList;


public class sampleBoundary {

    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private TextField brillanza;
    @FXML
    private TableView listaFilamenti;
    @FXML
    private Label label;
    @FXML
    protected void handleButtonAction(){
        i=i+20;
        ricercaContrastoEllitticita();
    }

    private int i=1;



    public void initialize() {


        ObservableList<String> valor =
                FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10");

        combo1.setItems(valor);

        ObservableList<String> valo =
                FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10");

        combo2.setItems(valo);

        listaFilamenti.setEditable(true);

        TableColumn colID = new TableColumn("ID");


        colID.setCellValueFactory(
                new PropertyValueFactory<Filament,Integer>("ID")
        );

        listaFilamenti.getColumns().addAll(colID);


    }

    public void ricercaContrastoEllitticita(){

        try{
            String contenutoCombo1 = combo1.getSelectionModel().getSelectedItem();
            String contenutoCombo2 = combo2.getSelectionModel().getSelectedItem();
            String bri = brillanza.getText();


            BrightnessController BC = new BrightnessController() ;

            ArrayList<Filament> arrayfil = BC.brightnessCntrl(Integer.parseInt(contenutoCombo1),
                    Integer.parseInt(contenutoCombo2),Double.parseDouble(bri));

            double percentage = BC.brightnessPercentage();

            eseguiMetodo(arrayfil,percentage,i);







    } catch(EllipticityException ee){
            System.out.println("AA");
        } catch (NegativeValuesException nve){
            System.out.println("no");
        }


}

    public void eseguiMetodo(ArrayList<Filament> listf,double percentage,int i){
        Filament fil = new Filament(0,null,0,0,
                0,0,null,null);
        int y=20;



        if (listf.size() != 0) {

            fil = listf.get(i-1);

            ObservableList<Filament> filamento =
                    FXCollections.observableArrayList(
                            fil
                    );
            if(i+20 > listf.size()){y = i-listf.size();}

            for (int k = i; k < i+y; k++) {

               fil = listf.get(k);
                filamento.add(fil);
                listaFilamenti.setItems(filamento);


            }

        }
        label.setText("percentuale: "+String.valueOf(percentage)+"%");
    }

}

