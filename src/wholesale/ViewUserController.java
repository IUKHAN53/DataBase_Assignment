package wholesale;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ViewUserController implements Initializable {

    DBconnect db;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String Query;
    
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXTextField NameFld;
    @FXML
    private JFXTextField UnameFld;
    @FXML
    private JFXTextField EmailFld;
    @FXML
    private JFXTextField TypeFld;
    @FXML
    private JFXTextField DesigFld;
    @FXML
    private JFXTextField AdrsFld;
    @FXML
    private JFXTextField PassFld;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db=new DBconnect();
        try {
          con=db.DBconnects();
          Query="Select * from Users where Name=?";
            System.out.println(Query);
          pst=con.prepareStatement(Query);
          pst.setString(1,LoginPageController.Username);
          rs=pst.executeQuery();
          while(rs.next()){
          NameFld.setText(rs.getString("Name"));
          UnameFld.setText(rs.getString("UserName"));
          PassFld.setText(rs.getString("PASS"));
          TypeFld.setText(rs.getString("UserType"));
          DesigFld.setText(rs.getString("Designation"));
          EmailFld.setText(rs.getString("Email"));
          AdrsFld.setText(rs.getString("Address"));
          }
        } catch (Exception ex) { }
    }    
    @FXML
    private void chngName(ActionEvent event) {
        change("Name",NameFld.getText());
    }

    @FXML
    private void ChngEmail(ActionEvent event) {
        change("Email",EmailFld.getText());
    }

    @FXML
    private void ChngUname(ActionEvent event) {
        change("UserName",UnameFld.getText());
    }

    @FXML
    private void chngAdress(ActionEvent event) {
        change("Address",AdrsFld.getText());
    }

    @FXML
    private void ChngPass(ActionEvent event) {
        change("PASS",PassFld.getText());
    }
    
    void change(String Value,String data){
        try {
            con=db.DBconnects();
              Query="Update Table Users set ? = ? where Name=?";
              pst=con.prepareStatement(Query);
              pst.setString(1,Value);
              pst.setString(2,data);
              pst.setString(3, LoginPageController.Username);
               } catch (Exception ex) {
            Logger.getLogger(ViewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
