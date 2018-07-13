package boundary;

import control.MiscController;
import entity.Satellite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;




public class admin3Boundary {
    @FXML
    private TextField nome;
    @FXML
    private TextField first;
    @FXML
    private TextField last;
    @FXML
    private Label lab;
    @FXML
    private Button GoBack;

    public void inserisci() {

        String n = nome.getText();
        String f = first.getText();
        String l = last.getText();
        boolean c;

        MiscController MC = new MiscController();
        Satellite S = new Satellite(n,f,l);
        c = MC.addRepository(S);

        if (c == true){
            lab.setText("INSERIMENTO COMPLEATO");

        }else{
            lab.setText("INSERIMENTO FALLITO");

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
