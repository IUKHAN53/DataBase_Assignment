package wholesale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class DrawerContentController implements Initializable {

    @FXML
    private Label loginasLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginasLbl.setText("As: "+LoginPageController.Username+"("+LoginPageController.type+")");
        
    }    
    
}
