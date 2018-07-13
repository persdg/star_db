package boundary;

import control.StarController;
import exception.NegativeValuesException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class req9Boundary {
    @FXML
    private TextField IDStrutturaEstesa;
    @FXML
    private Label numberOfStars;
    @FXML
    private Label UNBPercentage;
    @FXML
    private Label PREPercentage;
    @FXML
    private Label PROPercentage;
    @FXML
    private Label negativeValuesError;
    @FXML
    private Button GoBack;

    public void ricercaStrutturaEstesa() {

        String ID = IDStrutturaEstesa.getText();

        try {
            negativeValuesError.setText("");
            StarController SC = new StarController();
            double[] values = SC.starsInBoundary(Integer.parseInt(IDStrutturaEstesa.getText()));

            if (values[0] == 0){negativeValuesError.setText("FILAMENTO NON CONTIENE STELLE");}

            numberOfStars.setText(String.valueOf(values[0]));
            UNBPercentage.setText(String.valueOf(values[1]));
            PREPercentage.setText(String.valueOf(values[2]));
            PROPercentage.setText(String.valueOf(values[3]));

        } catch (NegativeValuesException e) {
            negativeValuesError.setText("INSERIRE VALORI POSITIVI");
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
