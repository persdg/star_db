package boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import control.FileImporterController;
import javafx.stage.Stage;

import java.io.IOException;


public class admin1Boundary {

    @FXML
    private TextField txt;
    @FXML
    private Button GoBack;
    @FXML
    private Label lbl;


    public void Import() {

        String file = txt.getText();


            lbl.setText("");

            FileImporterController FI = new FileImporterController();

            FI.ImportFile(file);

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

