package boundary;

import entity.Filament;
import entity.FilamentInfo;
import exception.NegativeValuesException;
import exception.SegmentRangeException;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import control.FilamentController;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import boundary.GlobalVar;


public class req7Boundary {

    @FXML
    private TableView filamentitabella;
    @FXML
    private Button GoBack;
    @FXML
    private TextField numMin;
    @FXML
    private TextField numMax;
    @FXML
    protected void handleButtonAction(){
        i=i+20;
        ricerca();
    }
    @FXML
    private Label eccezioni;


    private int i =1;

    public void ricerca() {

        try {
            eccezioni.setText("");

            String min = numMin.getText();
            String max = numMax.getText();


            FilamentController FC = new FilamentController();

            ArrayList<Filament> array = FC.filamentNumSegments(Integer.parseInt(min), Integer.parseInt(max));

            eseguiMetodo(array,i);



        }catch(SegmentRangeException sre){
            eccezioni.setText("INSERIRE RANGE MAGGIORE DI 2 O RANGE VALIDO");
        }catch(NegativeValuesException nve) {
            eccezioni.setText("INSERIRE VALORI POSITIVI");

        }

    }
    public void initialize() {

        filamentitabella.setEditable(true);


        TableColumn colID = new TableColumn("ID");


        colID.setCellValueFactory(
                new PropertyValueFactory<FilamentInfo,Integer>("ID")
        );



        filamentitabella.getColumns().addAll(
                colID
        );


    }

    public void eseguiMetodo(ArrayList<Filament> filList,int i){

        Filament fil = new Filament(0,null,0,0,
                0,0,null,null);
        int y=20;



        if (filList.size() != 0) {

            fil = filList.get(i-1);

            ObservableList<Filament> filamento =
                    FXCollections.observableArrayList(
                            fil
                    );
            if(i+20 > filList.size()){y = i-filList.size();}

            for (int k = i; k < i+y; k++) {

                fil = filList.get(k);
                filamento.add(fil);
                filamentitabella.setItems(filamento);

            }

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
