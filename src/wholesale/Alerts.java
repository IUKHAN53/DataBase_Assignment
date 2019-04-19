package wholesale;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
public class Alerts {
    public static void showAlert(Alert.AlertType type,String title,String Message){
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText("Attention");
            alert.setContentText(Message);
            alert.showAndWait();    
    }
}
