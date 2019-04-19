package wholesale;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class AddstockPageController implements Initializable {

    Connection con;
    String name,comments;
    int quantity;
    DBconnect db;
    
    
    @FXML
    private TextField NameFld;

    @FXML
    private TextField QuantityFld;

    @FXML
    private TextArea CommentFld;

    @FXML
    private TextField PriceFld;

    @FXML
    void CofirmAdd(ActionEvent event){
        System.out.println("Pressed");
        name=NameFld.getText();
        String quan=QuantityFld.getText();
        int price=Integer.parseInt(PriceFld.getText());
            quantity=Integer.parseInt(quan);
            comments=CommentFld.getText();
        try {
            con=db.DBconnects();
            String query="Insert into stock(name,Price,quantity,comments,Date_added) values(?,?,?,?,sysdate())";   
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, price);
            pst.setInt(3, quantity);
            pst.setString(4,comments);
            pst.execute();
            MainPageController.changes+="Stock, ";
            pst.close();
//            query="Select stock_id,Price from stock where name=?";
//            pst=con.prepareStatement(query);
//            pst.setString(1, name);
//            ResultSet rs=pst.executeQuery();
//            while(rs.next()){
//                String query2="Insert into Price(Stock_id,PerPrice) values(?,?)";
//                PreparedStatement ps2=con.prepareStatement(query2);
//                ps2.setString(1, rs.getString("stock_id"));
//                ps2.setInt(2, rs.getInt("Price"));
//                ps2.execute();
//                ps2.close();
//                
//            }
             } catch (Exception ex) {
                 System.out.println(ex);
        }
            System.out.println("Done");
            Alerts.showAlert(Alert.AlertType.INFORMATION,"Succes","Data Added Succesfully");
            QuantityFld.setText("");
            NameFld.setText("");
            CommentFld.setText("");
            PriceFld.setText("");
            }

    boolean isNumeric(String str){
      NumberFormat formatter = NumberFormat.getInstance();
      ParsePosition pos = new ParsePosition(0);
      formatter.parse(str, pos);
      return str.length() == pos.getIndex();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Is here");
        db=new DBconnect();
    }    


    
}
