package boundary;


import control.FilamentController;
import entity.FilamentInfo;

import exception.NegativeValuesException;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.cell.PropertyValueFactory;

import boundary.GlobalVar;
import javafx.stage.Stage;

import java.io.IOException;


public class req5Boundary{

    @FXML
    private TextField IDFilamento;
    @FXML
    private TextField NomeFilamento;
    @FXML
    private TableView listaElementi;
    @FXML
    private Label NegativeValues;
    @FXML
    private Button GoBack;


    public void ricercaFilamentoID() {

        try {
            NegativeValues.setText("");

            String ID = IDFilamento.getText();

            FilamentController FC = new FilamentController();

            FilamentInfo u = FC.filamentId(Integer.parseInt(ID));

            if(u == null){NegativeValues.setText("FILAMENTO NON TROVATO");}

            eseguiMetodo(u);
        }catch(NegativeValuesException nve){NegativeValues.setText("INSERIRE ID MAGGIORI DI 0");}
    }

    public void ricercaFilamentoNome() {

        String name = NomeFilamento.getText();

        FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentName(name);

        if(u == null){NegativeValues.setText("FILAMENTO NON TROVATO");}

        eseguiMetodo(u);

    }


    public void initialize() {

        listaElementi.setEditable(true);


        TableColumn colLat = new TableColumn("Latitudine");
        TableColumn colLon = new TableColumn("Longitudine");
        TableColumn collAltezza = new TableColumn("Altezza");
        TableColumn collLunghezza = new TableColumn("Lunghezza");
        TableColumn collNumFilamenti = new TableColumn("Numero Segmenti");

        colLat.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("glat")
        );

        colLon.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("glon")
        );

        collAltezza.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("height")
        );

        collLunghezza.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("width")
        );

        collNumFilamenti.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Integer>("count")
        );


        listaElementi.getColumns().addAll(
                colLat, colLon, collAltezza, collLunghezza, collNumFilamenti
        );

    }

    public void eseguiMetodo(FilamentInfo FI){

        if (FI != null) {

            ObservableList<FilamentInfo> filamento =
                    FXCollections.observableArrayList(
                            FI
                    );
            listaElementi.setItems(filamento);
        }
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

