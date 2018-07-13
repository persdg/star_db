package boundary;


import control.StarController;
import entity.StarInfo;
import exception.NegativeValuesException;
import exception.NotASortTypeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class req12Boundary {

    @FXML
    private TextField IDFilamento;
    @FXML
    private TableView listaStelle;
    @FXML
    private Button GoBack;
    @FXML
    private TextField TipoOrdinamento;
    @FXML
    protected void handleButtonAction(){
        i=i+20;
        ricercaFilamento();
    }
    @FXML
    private Label eccezioni;

    private int i=1;

    public void initialize() {


        listaStelle.setEditable(true);

        TableColumn colIDstar = new TableColumn("ID");
        TableColumn colIDdens = new TableColumn("Distanza");
        TableColumn colIDflux = new TableColumn("Flusso");


        colIDstar.setCellValueFactory(
                new PropertyValueFactory<StarInfo, Integer>("id")
        );
        colIDdens.setCellValueFactory(
                new PropertyValueFactory<StarInfo, String>("distance")
        );
        colIDflux.setCellValueFactory(
                new PropertyValueFactory<StarInfo, String>("flux")
        );

        listaStelle.getColumns().addAll(
                colIDstar, colIDdens, colIDflux

        );


    }

    public void ricercaFilamento() {

        try {

            String ID = IDFilamento.getText();
            String sort = TipoOrdinamento.getText();

            StarController SC = new StarController();

            ArrayList<StarInfo> si = SC.Distance(Integer.parseInt(ID), sort);

            eseguiMetodo(si,i);


        }catch(NotASortTypeException naste){
            eccezioni.setText("INSERIRE TIPO flux O distance");
        }catch(NegativeValuesException nve){
            eccezioni.setText("INSERIRE VALORI POSITIVI");
        }


    }

    public void eseguiMetodo(ArrayList<StarInfo> starlist,int i){

        int y=20;

        StarInfo si;
        if (starlist.size() != 0) {

            si = starlist.get(i-1);

            ObservableList<StarInfo> star =
                    FXCollections.observableArrayList(
                            si
                    );
            if(i+20 > starlist.size()){y = i-starlist.size();}

            for (int k = i; k < i+y; k++) {

                si = starlist.get(k);
                star.add(si);
                listaStelle.setItems(star);


            }

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

