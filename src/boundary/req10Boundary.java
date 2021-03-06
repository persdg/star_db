package boundary;

import control.FilamentController;
import control.StarController;
import entity.FilamentInfo;
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

public class req10Boundary {
    @FXML
    private TextField Base;
    @FXML
    private TextField Altezza;
    @FXML
    private TextField X;
    @FXML
    private TextField Y;
    @FXML
    private Label STFC;
    @FXML
    private Label FCPROTO;
    @FXML
    private Label FCPRE;
    @FXML
    private Label FCU;
    @FXML
    private Label ST;
    @FXML
    private Label CPROTO;
    @FXML
    private Label CPRE;
    @FXML
    private Label CU;
    @FXML
    private Label ERR;
    @FXML
    private Button GoBack;


    public void ricercaRegione() {

        try {

            String B = Base.getText();
            String A = Altezza.getText();
            String XX = X.getText();
            String YY = Y.getText();

            StarController SC = new StarController();

            double [] array = SC.starsInRectControl(Float.parseFloat(YY),
                    Float.parseFloat(XX), Float.parseFloat(B),Float.parseFloat(A));

            ST.setText(String.valueOf(array[0]));
            CPROTO.setText(String.valueOf(array[1]));
            CPRE.setText(String.valueOf(array[2]));
            CU.setText(String.valueOf(array[3]));
            STFC.setText(String.valueOf(array[4]));
            FCPROTO.setText(String.valueOf(array[5]));
            FCPRE.setText(String.valueOf(array[6]));
            FCU.setText(String.valueOf(array[7]));


        }catch(NegativeValuesException nve){
            ERR.setText("INSERIRE VALORI POSITIVI");
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
