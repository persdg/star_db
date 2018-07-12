package boundary;

import entity.FilamentInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import persistence.FilamentRepository;

public class req9Boundary {
    @FXML
    private TextField IDStrutturaEstesa;

    public void ricercaStrutturaEstesa() {

        String ID = IDStrutturaEstesa.getText();

        /* METTERE ISTRUZIONI PER RICERCA STUTTURA
        FilamentRepository SE = new FilamentRepository() ;

        FilamentInfo u = FR.searchFilaments(Integer.parseInt(ID));
        System.out.println(u);*/

    }
}
