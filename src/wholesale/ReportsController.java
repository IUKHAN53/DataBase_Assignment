
package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ReportsController implements Initializable {

    DBconnect db;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String Query;
    
    @FXML
    private AnchorPane mainPane;
    private javax.swing.JTable ReportTable;
     private javax.swing.JScrollPane jScrollPane1;
    @FXML
    private AnchorPane Profits;
    @FXML
    private TextField salesFld;
    @FXML
    private TextField SoldFld;
    @FXML
    private TextField AddedstckFld;
    @FXML
    private TextField ProfFld;
    @FXML
    private TextField RsrecFld;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        db=new DBconnect();
        con=db.DBconnects();
        Query="Select Sum(Paid) as total,Sum(Quantity) as sold from Buyer";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            salesFld.setText(""+rs.getInt("total"));
            SoldFld.setText(""+rs.getInt("sold"));
            RsrecFld.setText(""+rs.getInt("total"));
        }
        int total=0;
        pst.close();
        Query="Select quantity,Price from stock";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            total=total+(rs.getInt("quantity")*rs.getInt("Price"));
        }
        AddedstckFld.setText(""+total);
        pst.close();
        Query="Select sum(quantity) as q from stock";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next()){
            ProfFld.setText(""+rs.getInt("q"));
        }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
    

