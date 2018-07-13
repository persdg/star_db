package boundary;


import exception.FilamentNotFoundException;
import exception.NegativeValuesException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.*;
import control.SegmentController;
import javafx.stage.Stage;

import java.io.IOException;


public class req11Boundary {

    @FXML
    private TextField idView;
    @FXML
    private Label firstDistView;
    @FXML
    private Label lastDistView;
    @FXML
    private Label negativeValuesError;
    @FXML
    private Button GoBack;

    public void ricercaStrutturaEstesa() {

        String ID = idView.getText();

        try {
            negativeValuesError.setText("");


            SegmentController SC = new SegmentController();
            double[] values = SC.extremesDistances(Integer.parseInt(ID));


            lastDistView.setText(String.valueOf(values[0]));
            firstDistView.setText(String.valueOf(values[1]));

        } catch (NegativeValuesException e) {
            negativeValuesError.setText("INSERIRE VALORI POSITIVI");
        }catch(FilamentNotFoundException fnfe){
            negativeValuesError.setText("FILAMENTO NON TROVATO");
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
