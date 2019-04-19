
package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddCustomerController implements Initializable {

    DBconnect db;
    PreparedStatement pst;
    Connection con;
    
    String name,email,address,company,remarks,phone;

    @FXML
    private TextField nameFld;
    @FXML
    private TextField phoneFld;
    @FXML
    private TextField AddressFld;
    @FXML
    private TextField EmailFld;
    @FXML
    private TextField CompanyFld;
    @FXML
    private TextField RemarksFLd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db=new DBconnect();
    }    

    @FXML
    private void addCustomer(ActionEvent event) throws Exception{
        name=nameFld.getText();
        phone=phoneFld.getText();
        address=AddressFld.getText();
        email=EmailFld.getText();
        company=CompanyFld.getText();
        remarks=RemarksFLd.getText();
        con=db.DBconnects();
        String Query="Insert Into Customer(Name,Phone,Address,Email,Company,Remarks,Date_added) values(?,?,?,?,?,?,sysdate())";
        pst=con.prepareStatement(Query);
        pst.setString(1, name);
        pst.setString(2, phone);
        pst.setString(3, address);
        pst.setString(4, email);
        pst.setString(5, company);
        pst.setString(6, remarks);
        pst.execute();
        MainPageController.changes+="Customer, ";
        Alerts.showAlert(Alert.AlertType.INFORMATION, "Sucess","Added SuccesFully");
    }
    @FXML
    private void Cancel(ActionEvent event) {
    }
    
}
