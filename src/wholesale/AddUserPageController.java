
package wholesale;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class AddUserPageController implements Initializable {

    String name,email,designation,adress,username,password,confirmPass,usertype;
    DBconnect db;
    
     @FXML
    private TextField NameFld;

    @FXML
    private TextField EmailFld;

    @FXML
    private TextField DesigFld;

    @FXML
    private TextField AdressFld;

    @FXML
    private TextField UnameFld;

    @FXML
    private PasswordField PassFld;

    @FXML
    private PasswordField ConPassFld;

    @FXML
    private JFXButton AddBtn;

    @FXML
    private JFXButton CancelBtn;
    
     @FXML
    private ComboBox<String> typecombo;

    @FXML
    void AddUser(ActionEvent event){
        name=NameFld.getText();
        email=EmailFld.getText().toLowerCase();
        designation=DesigFld.getText();
        adress=AdressFld.getText();
        username=UnameFld.getText();
        password=PassFld.getText();
        confirmPass=ConPassFld.getText();
        usertype=typecombo.getSelectionModel().getSelectedItem().toString();
        Connection con;  
        try {
            con = db.DBconnects();
        if(isEmpty().equals(" ")){
            if(password.equals(confirmPass)){
                String query="Insert into Users(Name,Email,Designation,Adress,"
                        + "UserName,PASS,UserType,Date_added) values(?,?,?,?,?,?,?,sysdate())";
                PreparedStatement pst=con.prepareStatement(query);

                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, designation);
                pst.setString(4, adress);
                pst.setString(5, username);
                pst.setString(6, password);
                pst.setString(7, usertype);
                pst.execute();
                MainPageController.changes+="User, ";
                Alerts.showAlert(AlertType.INFORMATION, "Succes","Added Succesfully");
            }
            else
                Alerts.showAlert(AlertType.ERROR,"Password Error", "Passwords MissMatch");
        }  
        else
            Alerts.showAlert(AlertType.ERROR, "Empty Field", ""
                    + "Please fill the "+isEmpty()+" Field");
        }
        catch (SQLIntegrityConstraintViolationException exe) {
          Alerts.showAlert(AlertType.ERROR, "Username Exists", "Enter Another UserName!");}
        catch (SQLException e) {System.out.println(e);} 
        
        catch (Exception ex) {System.out.println(ex);        }
    }

    @FXML
    void CancelAdd(ActionEvent event) {

    }
    String isEmpty(){

        if(name.isEmpty())
            return "Name";
        else if(designation.isEmpty())
            return "Designation";
        else if(adress.isEmpty())
            return "Address";
        else if(username.isEmpty())
            return "Username";
        else if(password.isEmpty())
            return "Password";
        else if(usertype.isEmpty())
            return "UserType";
        
        return " ";
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       db=new DBconnect();
       usertype();
    }
    void usertype() {
         try {
            Connection con=db.DBconnects();
            String Query="Select * from usertype";
            PreparedStatement ps=con.prepareStatement(Query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
                typecombo.getItems().add(rs.getString("UserType"));           
        } catch (Exception ex) {
             System.out.println(ex);
        }}      
    }    

