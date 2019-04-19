package wholesale;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class AddBuyerController implements Initializable {

    private DBconnect db;
    static Connection con;
    String name,payment,phone;
    int Quantity;
    String Query;
    PreparedStatement pst;
    ResultSet rs;
    int price;
    boolean flag=true;
    SpinnerValueFactory<Integer> value;

    @FXML
    private TextField NameFld;

    @FXML
    private ComboBox<String> PaymentFld;
    @FXML
    private JFXButton AddBuyerBtn;
    @FXML
    private ComboBox<String> StockFld;
    @FXML
    private Spinner<Integer> QuantitySpinner;
    @FXML
    private TextField phoneFld;
    @FXML
    private TextField perprice;
    @FXML
    private TextField totalprice;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    db=new DBconnect();
    try {
        con=db.DBconnects();
        PaymentFld.getItems().add("Credit Card");
        PaymentFld.getItems().add("Cash");
        PaymentFld.getItems().add("Bitcoin");
        Query="Select name from stock";
        pst=con.prepareStatement(Query);
        rs=pst.executeQuery();
        while(rs.next())
            StockFld.getItems().add(rs.getString("name"));   
        StockFld.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    con=db.DBconnects();
                    String item=StockFld.getValue();
                    Query="select quantity,Price from stock where name=?";
                    PreparedStatement pst=con.prepareStatement(Query);
                    pst.setString(1,item);
                    ResultSet rs=pst.executeQuery();
                    while(rs.next()){
                        value=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,rs.getInt("quantity"),1);
                        price=rs.getInt("Price");
                        Quantity=rs.getInt("quantity");
                    }
                    QuantitySpinner.setValueFactory(value);
                    perprice.setText(Integer.toString(price));
                    totalprice.setText(Integer.toString(price*QuantitySpinner.getValue()));
                } 
                catch (Exception ex) {System.out.println(ex);}}});
                 QuantitySpinner.getEditor().selectionProperty().addListener((observable) -> {
                 totalprice.setText(Integer.toString(price*QuantitySpinner.getValue()));
        });

         } catch (Exception ex) {
             System.out.println(ex);
    }
    
    
    }
    @FXML
    private void AddBuyer(ActionEvent event) {
        name=NameFld.getText();
        phone=phoneFld.getText();
        payment=PaymentFld.getValue();
        try {
            con=db.DBconnects();
        Query="Insert Into Buyer(Name,Quantity,Payment,Stock,Paid,Date) values(?,?,?,?,?,sysdate())";
        pst=con.prepareStatement(Query);
        pst.setString(1, name);
        pst.setInt(2, QuantitySpinner.getValue());
        pst.setString(3, payment);
        pst.setString(4, StockFld.getValue());
        pst.setInt(5, Integer.parseInt(totalprice.getText()));
        pst.execute();
        Alerts.showAlert(Alert.AlertType.INFORMATION,"Sucess", "Added Succesfully");
        pst.close();
        Query="Update stock set quantity=? where name=?";
        pst=con.prepareStatement(Query);
        pst.setInt(1,Quantity-QuantitySpinner.getValue());
        pst.setString(2, StockFld.getValue());
        pst.execute();
        pst.close();
        Query="Insert into reciept(Buyer_name,Stock_bought,Quantity,Paid,"
                + "DateAndTime) values(?,?,?,?,sysdate()) ";
        pst=con.prepareStatement(Query);
        pst.setString(1, name);
        pst.setString(2, StockFld.getValue());
        pst.setInt(3, QuantitySpinner.getValue());
        pst.setInt(4, Integer.parseInt(totalprice.getText()));
        pst.execute();
        pst.close();
        MainPageController.changes+="Buyer, ";
         } catch (Exception ex) {
             System.out.println(ex);
        }
            }
    }
