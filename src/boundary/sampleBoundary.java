package boundary;

import control.BrightnessController;
import entity.Filament;
import entity.FilamentInfo;
import exception.EllipticityException;
import exception.NegativeValuesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import persistence.FilamentRepository;

import java.io.IOException;
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
    private Button GoBack;
    @FXML
    private Label label;
    @FXML
    protected void handleButtonAction(){
        i=i+20;
        ricercaContrastoEllitticita();
    }
    @FXML
    private Label eccezioni;

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

            eccezioni.setText("");

            String contenutoCombo1 = combo1.getSelectionModel().getSelectedItem();
            String contenutoCombo2 = combo2.getSelectionModel().getSelectedItem();
            String bri = brillanza.getText();


            BrightnessController BC = new BrightnessController() ;

            ArrayList<Filament> arrayfil = BC.brightnessCntrl(Integer.parseInt(contenutoCombo1),
                    Integer.parseInt(contenutoCombo2),Double.parseDouble(bri));

            double percentage = BC.brightnessPercentage();

            eseguiMetodo(arrayfil,percentage,i);







    } catch(EllipticityException ee){
            eccezioni.setText("INSERIRE RANGE VALIDI");
        } catch (NegativeValuesException nve){
            eccezioni.setText("INSERIRE VALORI POSITIVI");
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

    public void goBack() {

        try {
            if (GlobalVar.USERTYPE) {

                Stage stage = (Stage) GoBack.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleAdmin.fxml"));
                Parent root1 = null;
                root1 = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("UTENTE");
                stage1.setScene(new Scene(root1, 800, 400));
                stage1.show();
            } else {

                GlobalVar.USERTYPE = false;
                Stage stage = (Stage) GoBack.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleUtente.fxml"));
                Parent root1 = null;
                root1 = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("UTENTE");
                stage1.setScene(new Scene(root1, 800, 400));
                stage1.show();
            }


        }catch(IOException e){}
    }

}

