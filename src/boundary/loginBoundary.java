package boundary;



import control.LogInController;
import entity.Admin;

import exception.UserNotFoundException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import boundary.GlobalVar;

public class loginBoundary {

    @FXML
    private Button login_button;
    @FXML
    private Label error_label;
    @FXML
    private TextField username,password;

    private String usern;
    private String pas;



    @FXML
    public void log(){

                        try {
                            usern = username.getText();
                            pas = password.getText();

                            error_label.setText("");



                            boolean usertype;
                            LogInController LC = new LogInController();


                            usertype = LC.LogIn(username.getText(), password.getText());

                            if(usertype) {
                                GlobalVar.USERTYPE = true;
                                Stage stage = (Stage) login_button.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleAdmin.fxml"));
                                Parent root1 = null;
                                root1 = (Parent) fxmlLoader.load();
                                Stage stage1 = new Stage();
                                stage1.setTitle("UTENTE");
                                stage1.setScene(new Scene(root1,800,400));
                                stage1.show();
                            }else {
                                GlobalVar.USERTYPE = false;
                                Stage stage = (Stage) login_button.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/principaleUtente.fxml"));
                                Parent root1 = null;
                                root1 = (Parent) fxmlLoader.load();
                                Stage stage1 = new Stage();
                                stage1.setTitle("UTENTE");
                                stage1.setScene(new Scene(root1,800,400));
                                stage1.show();
                            }


                        }catch(UserNotFoundException unfe){
                            error_label.setText("USERNAME O PASSWORD ERRATI");
                        }catch(IOException io){};
                        

                    }

    }





