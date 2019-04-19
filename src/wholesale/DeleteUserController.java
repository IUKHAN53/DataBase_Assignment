
package wholesale;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class DeleteUserController implements Initializable {

    DBconnect db;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String Query;
    
    @FXML
    private JFXButton Delete;
    @FXML
    private JFXComboBox<String> usercombo;
    @FXML
    private JFXTextField NameFld;
    @FXML
    private JFXTextField EmailFLd;
    @FXML
    private JFXTextField AdressFld;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db=new DBconnect();
        Query="Select UserName from Users";
        try {
            con=db.DBconnects();
            pst=con.prepareStatement(Query);
            rs=pst.executeQuery();
            while(rs.next())
                usercombo.getItems().add(rs.getString("UserName"));
            usercombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    String user=usercombo.getValue();
                    Query="select Name,Adress,Email from Users where UserName=?";
                    pst=con.prepareStatement(Query);
                    pst.setString(1,user);
                    ResultSet rs=pst.executeQuery();
                    while(rs.next()){
                        NameFld.setText(rs.getString("Name"));
                        EmailFLd.setText(rs.getString("Email"));
                        AdressFld.setText(rs.getString("Adress"));
                    }
                } catch (Exception ex) {}}});
        } catch (Exception ex) {
        }
    }    

    @FXML
    private void Delete(ActionEvent event) throws Exception{
        con=db.DBconnects();
        Query="delete from Users where UserName=?";
        pst=con.prepareStatement(Query);
        pst.setString(1, usercombo.getValue());
        pst.execute();
        Alerts.showAlert(Alert.AlertType.INFORMATION,"Success", "User Deleted Succesfully");
    }
    
}
