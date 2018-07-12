package boundary;

import control.FilamentController;
import entity.Filament;
import entity.FilamentInfo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import persistence.FilamentRepository;

public class req5Boundary {
    @FXML
    private TextField IDFilamento;
    @FXML
    private TextField NomeFilamento;
    @FXML
    private TableView listaElementi;


    public FilamentInfo ricercaFilamentoID() {

        String ID = IDFilamento.getText();

        FilamentController FC = new FilamentController() ;

        FilamentInfo u = FC.filamentId(Integer.parseInt(ID));
        return u;
       // System.out.println(u);



    }

    public void ricercaFilamentoNome() {

        String N = NomeFilamento.getText();

        FilamentController FR = new FilamentController() ;

        FilamentInfo u = FR.filamentId(Integer.parseInt(N));
        System.out.println(u);



    }


    public void initialize() {

        listaElementi.setEditable(true);

        float lon;

        FilamentInfo FInfo = ricercaFilamentoID();
        lon = FInfo.getX();

        TableColumn collLongitudine = new TableColumn("Longitudine");
        TableColumn collLatitudine = new TableColumn("Latitudine");
        TableColumn collAltezza = new TableColumn("Altezza");
        TableColumn collLunghezza = new TableColumn("Lunghezza");
        TableColumn collNumFilamenti = new TableColumn("Numero Filamenti");

        collLongitudine.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo, Float>("Longitudine")
        );
        collLatitudine.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("Latitudine")
        );
        collAltezza.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("Altezza")
        );
        collLunghezza.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Float>("Lunghezza")
        );
        collNumFilamenti.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Integer>("Numero Filamenti")
        );


        listaElementi.getColumns().addAll(
                collLongitudine, collLatitudine, collAltezza, collLunghezza, collNumFilamenti
        );
}
}

