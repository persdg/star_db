package boundary;

import exception.AlreadyInDBException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import control.MiscController;
import entity.Instrument;
import javafx.stage.Stage;

import java.io.IOException;


public class admin4Boundary {
    @FXML
    private TextField nome;
    @FXML
    private TextField satellite;
    @FXML
    private TextField banda;
    @FXML
    private Label lab;
    @FXML
    private Label lab2;
    @FXML
    private Button GoBack;

    public void inserisci() {

        try {


            String n = nome.getText();
            String s = satellite.getText();
            String b = banda.getText();
            boolean c1,c2;

            MiscController MC = new MiscController();
            Instrument S = new Instrument(n, s);

            c1 = MC.addInstrument(S);
            c2 = MC.addBand(Double.parseDouble(b),n);


            if (c1 == true) {
                lab.setText("INSERIMENTO STRUMENTO COMPLEATO");

            } else {
                lab.setText("INSERIMENTO STRUMENTO FALLITO");
            }

            if (c2 == true){
                lab2.setText("INSERIMENTO BANDA COMPLETATO");
            }else{
                lab2.setText("INSERIMENTO BANDA FALLITO");
            }

        }catch(AlreadyInDBException aid){

            lab.setText("INSERIMENTO FALLITO: SATELLITE INESISTENTE");
            lab2.setText("");

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
