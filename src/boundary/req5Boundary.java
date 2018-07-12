package boundary;


import control.FilamentController;
import entity.Filament;
import entity.FilamentInfo;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import persistence.FilamentRepository;

public class req5Boundary{

    @FXML
    private TextField IDFilamento;
    @FXML
    private TextField NomeFilamento;
    @FXML
    private TableView listaElementi;


    public void ricercaFilamentoID() {

        String ID = IDFilamento.getText();

        FilamentController FC = new FilamentController() ;

        FilamentInfo u = FC.filamentId(Integer.parseInt(ID));

        eseguiMetodo(u);
    }

    public void ricercaFilamentoNome() {

        String name = NomeFilamento.getText();

        FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentName(name);

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


}

