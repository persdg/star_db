package main;

import control.SegmentController;
import control.StarController;
import entity.StarInfo;
import exception.NegativeValuesException;
import exception.NotASortTypeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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
            StarController SC = new StarController();
            ArrayList<StarInfo> listStarInfo = SC.Distance(45, "distance");

            for (StarInfo SI : listStarInfo) {
                System.out.println(SI.getId() + " " + SI.getDistance() + " " + SI.getFlux());
            }
        } catch (NegativeValuesException e) {
            System.out.println("Uh oh");
        } catch (NotASortTypeException e) {
            System.out.println("Ah eh");
        }



    }
}