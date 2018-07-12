package boundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class principaleAdminBoundary{

    @FXML
    private Button go_to_req5;
    @FXML
    private Button go_to_req6;
    @FXML
    private Button go_to_req8;
    @FXML
    private Button go_to_req9;
    @FXML
    private Button go_to_req10;
    @FXML
    private Button go_to_req12;
    @FXML
    private Button go_to_nuovoFile;
    @FXML
    private Button go_to_nuovoUtente;
    @FXML
    private Button go_to_insSatellite;
    @FXML
    private Button go_to_insStrumenti;



    @FXML
    private void goToreq5() {
        try {

            Stage stage = (Stage) go_to_req5.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/req5.fxml"));
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
    private void goToReq6() {
        try {

            Stage stage = (Stage) go_to_req6.getScene().getWindow();

            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/sample.fxml"));
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
    @FXML
    private void goToReq8() {
        try {

            Stage stage = (Stage) go_to_req8.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/req8.fxml"));
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
    private void goToReq9() {
        try {

            Stage stage = (Stage) go_to_req9.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/req9.fxml"));
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
    private void goToReq10() {
        try {

            Stage stage = (Stage) go_to_req10.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/req10.fxml"));
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
    private void goToReq12() {
        try {

            Stage stage = (Stage) go_to_req12.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/req5.fxml"));
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
    private void goToNuovoFile() {
        try {

            Stage stage = (Stage) go_to_nuovoFile.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/admin1.fxml"));
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
    private void goToNuovoUtente() {
        try {

            Stage stage = (Stage) go_to_nuovoUtente.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/admin2.fxml"));
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
    private void goToInsSatellite() {
        try {

            Stage stage = (Stage) go_to_insSatellite.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/admin3.fxml"));
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
    private void goToInsStrumenti() {
        try {

            Stage stage = (Stage) go_to_insStrumenti.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/admin4.fxml"));
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
}
