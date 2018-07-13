package boundary;

import control.FilamentController;
import entity.Filament;
import entity.FilamentInfo;
import exception.NegativeValuesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import control.SquareCircleController;
import javafx.scene.control.cell.PropertyValueFactory;



import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static java.awt.SystemColor.control;

public class req8Boundary {
    @FXML
    private TextField raggio;
    @FXML
    private TextField lato;
    @FXML
    private TextField X;
    @FXML
    private TextField Y;
    @FXML
    private TableView tabellafilamenti;
    @FXML
    private Label eccezioni;
    @FXML
    private Button GoBack;


    public void initialize() {

        tabellafilamenti.setEditable(true);


        TableColumn colID = new TableColumn("ID");


        colID.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Integer>("ID")
        );



        tabellafilamenti.getColumns().addAll(
                colID
        );


    }


    public void ricercaCerchio() {

        try {
            eccezioni.setText("");


            String Raggio = raggio.getText();
            String XX = X.getText();
            String YY = Y.getText();

            SquareCircleController SCC = new SquareCircleController();

            List<Filament> array = SCC.circleControl(Float.parseFloat(YY), Float.parseFloat(XX),
                    Float.parseFloat(Raggio));

            eseguiMetodo(array);

        } catch (NegativeValuesException nve) {
            eccezioni.setText("INSERIRE VALORI POSITIVI");
        }
    }

    public void ricercaQuadrato() {

        try {
            eccezioni.setText("");

        String R = lato.getText();
        String XX = X.getText();
        String YY = Y.getText();

        SquareCircleController SCC = new SquareCircleController();

        List<Filament> array = SCC.squareControl(Float.parseFloat(YY), Float.parseFloat(XX),
                Float.parseFloat(R));


        eseguiMetodo(array);

    }catch (NegativeValuesException nve) {
            eccezioni.setText("INSERIRE VALORI POSITIVI");
        }
    }

    public void eseguiMetodo(List<Filament> filList){

        Filament fil;

        if (filList.size() != 0) {

            fil = filList.get(0);

            ObservableList<Filament> filamento =
                    FXCollections.observableArrayList(
                            fil
                    );
            for (int k = 1; k < filList.size(); k++) {

                fil = filList.get(k);
                filamento.add(fil);

            }

            tabellafilamenti.setItems(filamento);

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
