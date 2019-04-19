package wholesale;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
      DBconnect db;
      Parent root;
    @FXML
    private JFXPasswordField PassFld;

      @FXML
    private JFXButton LoginBtn;
      
    @FXML
    private JFXTextField UserFld;
    
    static public String Username,type;

    @FXML
    void Login(ActionEvent event) throws Exception{
        String Uname=UserFld.getText();
        String Pass=PassFld.getText();
        boolean x=true;
        
        Connection con=db.DBconnects();     
        ResultSet rs;
        
        String Query="Select * from Users Where UserName = ? and PASS = ?";
        PreparedStatement pst=con.prepareStatement(Query);
        pst.setString(1,Uname);
        pst.setString(2,Pass);
        rs=pst.executeQuery();
        
        while(rs.next()){
            Query="insert into logs(DateAndTime,UserName) values (sysdate(),?)";
            pst=con.prepareStatement(Query);
            pst.setString(1, Uname);
            pst.execute();
            Username=rs.getString("Name");
            type=rs.getString("UserType");
            Stage stage=(Stage) LoginBtn.getScene().getWindow();
            root=FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Scene scene2=new Scene(root);
            stage.setScene(scene2);
            stage.setFullScreen(true);
            stage.show();     
            x=false;
         }
        if(x){
            Alerts.showAlert(Alert.AlertType.ERROR, "Invalid Login", "Enter Correct Information");
            UserFld.setText("");
            PassFld.setText("");
        }}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         db=new DBconnect();
    }
    
}
