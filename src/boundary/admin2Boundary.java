package boundary;

import entity.Admin;
import entity.NormalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import control.LogInController;
import javafx.stage.Stage;

import java.io.IOException;

public class admin2Boundary {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> combo1;
    @FXML
    private Label labeler;
    @FXML
    private Button GoBack;

    public void initialize() {


        ObservableList<String> valor =
                FXCollections.observableArrayList("True", "False");

        combo1.setItems(valor);
    }

    public void registra() {

        String u = username.getText();
        String p = password.getText();
        String n = name.getText();
        String s = surname.getText();
        String e = email.getText();
        String a = combo1.getSelectionModel().getSelectedItem();

        boolean c;

        LogInController LC = new LogInController();

        if (a == "True") {

            Admin AD = new Admin(u, p, n, s, e);
            c = LC.SignUp(AD);

        } else {

            NormalUser NU = new NormalUser(u, p, n, s, e, false);
            c = LC.SignUp(NU);

        }

        if (c == true) {
            labeler.setText("UTENTE REGISTRATO");

        } else {
            labeler.setText("UTENTE NON REGISTRATO");
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
