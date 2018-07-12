package boundary;



import control.LogInController;
import entity.Admin;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class loginBoundary {

 /*   @FXML
    private Button login_button,reset_button;
    @FXML
    private Label error_label;
    @FXML
    private TextField username,password;
    private static loginBoundary instance = null;
    private Admin AD;


    public static loginBoundary getInstance() {
        if(instance == null)
            return instance;
        return instance = new loginBoundary();
    }

    @FXML
    public void initialize(){
        DropShadow shadow = new DropShadow();
        login_button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        login_button.setEffect(shadow);
                    }
                });
        login_button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        login_button.setEffect(null);
                    }
                });
        reset_button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        reset_button.setEffect(shadow);
                    }
                });
        reset_button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        reset_button.setEffect(null);
                    }
                });
        reset_button.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        username.setDisable(false);
                        password.setDisable(false);
                        username.setText("");
                        password.setText("");
                        error_label.setText("");
                    }
                });
        login_button.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {

                        error_label.setText("");
                        username.setDisable(true);
                        password.setDisable(true);


                       if(check_input(username.getText(),password.getText())) {
                            AD=LogInController.getInstance().LogIn(username.getText(),password.getText(),login_button);
                            if (ru==null) {
                                error_label.setText("INCORRECT DATA");
                            }
                            else try {

                                Stage stage = (Stage) username.getScene().getWindow();
                                stage.close();

                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/menu_registered_user.fxml"));
                                Parent root1 = null;

                                UC1_Boundary uc1 = new UC1_Boundary(ru);
                                fxmlLoader.setController(uc1);

                                root1 = (Parent) fxmlLoader.load();
                                Stage stage1 = new Stage();
                                stage1.setTitle("REGISTERED USER MENU");
                                stage1.setScene(new Scene(root1, 1047, 725));
                                stage1.show();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                        }
                        else {
                            username.setDisable(false);
                            password.setDisable(false);
                        }


                    }
                });
    }


    public boolean check_input(String user,String passw) {

        if(user.length()<5){
            error_label.setText("TOO SHORT USER");
            return false;
        }
        if(passw.length()<5) {
            error_label.setText("TOO SHORT PASSWORD");
            return false;
        }
        return true;
    }*/
}


