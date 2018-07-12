package boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainBoundary {

    @FXML
    private Button go_to_utente;
    @FXML
    private Button go_to_amministratore;



    @FXML
    private void goToUtenteSession() {
        try {

            Stage stage = (Stage) go_to_utente.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleUtente.fxml"));
            Parent root1 = null;
            root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setTitle("UTENTE");
            stage1.setScene(new Scene(root1,800,400));
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goToAmministratoreSession() {
        try {

            Stage stage = (Stage) go_to_amministratore.getScene().getWindow();

            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleAdmin.fxml"));
            Parent root1 = null;
            root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setTitle("AMMINISTRATORE");
            Scene scene = new Scene(root1,800,400);
            stage1.setScene(scene);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
