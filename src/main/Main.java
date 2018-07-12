package main;

import control.SegmentController;
import control.StarController;
import exception.NegativeValuesException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main /*extends Application*/ {

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                getClass().getResource("../fxml/main_menu.fxml"));
        primaryStage.setTitle("Progetto Star_DB");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }*/


    public static void main(String[] args) {
        //launch(args);

        try {
            SegmentController SC = new SegmentController();
            double[] list = SC.extremesDistances(26);

            for (double d : list) {
                System.out.println(d);
            }
        } catch (NegativeValuesException e) {
            System.out.println("Oh no :(");
        }
    }
}